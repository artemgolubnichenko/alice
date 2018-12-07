package com.issart.alice.wiffy.rest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.google.inject.Inject;
import com.issart.alice.wiffy.rest.dto.request.AliceRequest;
import com.issart.alice.wiffy.rest.dto.response.AliceReplicas;
import com.issart.alice.wiffy.rest.dto.response.AliceResponse;
import com.issart.alice.wiffy.rest.dto.response.Response;
import com.issart.alice.wiffy.service.IWiffyService;
import com.issart.alice.wiffy.service.WiffyCommand;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class WiffyApplication {

    @Inject
    private IWiffyService wiffyService;

    private Set<String> queue = Collections.synchronizedSet(new HashSet<>());

    private final static Logger LOGGER = Logger.getLogger(WiffyApplication.class);

    public AliceResponse handleAliceRequest(AliceRequest request) {
        boolean newSession = request.getSession().getNew();
        String rawCommand = request.getRequest().getCommand();

        AliceResponse response = new AliceResponse();
        if(newSession && (rawCommand.isEmpty()
            || request.getRequest().getNlu().getEntities().isEmpty())) {
            response.setResponse(new Response(AliceReplicas.INITIAL_MSG));
        } else {
            WiffyCommand command = WiffyCommand.parseCommand(rawCommand);
            String userId = request.getSession().getUserId();
            switch (command) {
                case GET_PASSWORD: {
                    String password = wiffyService.getPassword(userId, "wifi");
                    if (StringUtils.isNotEmpty(password)) {
                        response.setResponse(new Response(password));
                        response.getResponse().setEndSession(true);
                    } else {
                        response.setResponse(new Response(AliceReplicas.PASSWORD_NOT_SET_MSG));
                        response.getResponse().setEndSession(true);
                    }
                    break;
                }
                case SET_PASSWORD: {
                    queue.add(userId);
                    response.setResponse(new Response(AliceReplicas.PASSWORD_SET_MSG));
                    response.getResponse().setEndSession(false);
                    break;
                }
                case UNKNOWN: {
                    if(queue.contains(userId)) {
                        queue.remove(userId);
                        wiffyService.addOrUpdatePassword(userId, "wifi", rawCommand);
                        response.setResponse(new Response(AliceReplicas.PASSWORD_SUCCESSFULLY_SET_MSG));
                        response.getResponse().setEndSession(true);
                    } else {
                        response.setResponse(new Response(AliceReplicas.INITIAL_MSG));
                        response.getResponse().setEndSession(true);
                    }
                }
            }
        }
        response.setSession(request.getSession());
        response.getSession().setNew(null);
        response.getSession().setSkillId(null);
        response.setVersion(request.getVersion());
        return response;
    }
}