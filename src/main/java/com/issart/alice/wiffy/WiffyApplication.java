package com.issart.alice.wiffy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.google.inject.Inject;
import com.issart.alice.common.Application;
import com.issart.alice.rest.dto.request.AliceRequest;
import com.issart.alice.wiffy.rest.dto.response.AliceReplicas;
import com.issart.alice.rest.dto.response.AliceResponse;
import com.issart.alice.rest.dto.response.Response;
import com.issart.alice.wiffy.service.IWiffyService;
import com.issart.alice.wiffy.command.WiffyCommand;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class WiffyApplication extends Application {

    @Inject
    private IWiffyService wiffyService;

    private Set<String> usersQueue = Collections.synchronizedSet(new HashSet<>());
    private final String DEVICE_NAME = "wifi";
    private final static Logger LOGGER = Logger.getLogger(WiffyApplication.class);

    @Override
    protected AliceResponse processRequest(AliceRequest request) {
        AliceResponse response = new AliceResponse();
        if(newSession && (rawCommand.isEmpty()
            || request.getRequest().getNlu().getTokens().isEmpty())) {
            response.setResponse(new Response(AliceReplicas.INITIAL_MSG));
        } else {
            WiffyCommand command = WiffyCommand.parseCommand(rawCommand);
            String userId = request.getSession().getUserId();
            switch (command) {
                case GET_PASSWORD: {
                    response = handleGetPasswordCommand(userId);
                    break;
                }
                case SET_PASSWORD: {
                    response = handleSetPasswordCommand(userId);
                    break;
                }
                case UNKNOWN: {
                    response = handleUnknownCommand(userId, rawCommand);
                    break;
                }
            }
        }
        return response;
    }

    private AliceResponse handleGetPasswordCommand(String userId) {
        AliceResponse response = new AliceResponse();
        String password = wiffyService.getPassword(userId, DEVICE_NAME);
        if (StringUtils.isNotEmpty(password)) {
            response.setResponse(new Response(password));
            response.getResponse().setEndSession(true);
        } else {
            response.setResponse(new Response(AliceReplicas.PASSWORD_NOT_SET_MSG));
            response.getResponse().setEndSession(true);
        }
        return response;
    }

    private AliceResponse handleSetPasswordCommand(String userId) {
        AliceResponse response = new AliceResponse();
        usersQueue.add(userId);
        response.setResponse(new Response(AliceReplicas.PASSWORD_SET_MSG));
        response.getResponse().setEndSession(false);
        return response;
    }

    private AliceResponse handleUnknownCommand(String userId, String rawCommand) {
        AliceResponse response = new AliceResponse();
        if(usersQueue.contains(userId)) {
            usersQueue.remove(userId);
            wiffyService.addOrUpdatePassword(userId, DEVICE_NAME, rawCommand);
            response.setResponse(new Response(AliceReplicas.PASSWORD_SUCCESSFULLY_SET_MSG));
            response.getResponse().setEndSession(true);
        } else {
            response.setResponse(new Response(AliceReplicas.INITIAL_MSG));
            response.getResponse().setEndSession(true);
        }
        return response;
    }
}
