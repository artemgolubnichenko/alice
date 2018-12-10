package com.issart.alice.common;

import com.issart.alice.exchange.exception.ExchangeSkillException;
import com.issart.alice.rest.dto.request.AliceRequest;
import com.issart.alice.rest.dto.response.AliceResponse;
import com.issart.alice.rest.dto.response.Response;
import org.apache.log4j.Logger;

public abstract class Application {

    protected            boolean newSession;
    protected            String  rawCommand;
    private final static Logger  LOGGER = Logger.getLogger(Application.class);

    public AliceResponse handleAliceRequest(AliceRequest request) {
        newSession = request.getSession().getNew();
        rawCommand = request.getRequest().getCommand().toLowerCase();

        AliceResponse response = null;
        try {
            response = processRequest(request);
        } catch (ExchangeSkillException e) {
            response = new AliceResponse();
            response.setResponse(new Response(e.getMessage()));
            LOGGER.error(rawCommand, e);
        } catch (Exception ex) {
            response = new AliceResponse();
            response.setResponse(new Response("Алиса не смогла выполнить команду"));
            LOGGER.error(rawCommand, ex);
        }
        response.setSession(request.getSession());
        response.getSession().setNew(null);
        response.getSession().setSkillId(null);
        response.setVersion(request.getVersion());
        return response;
    }

    protected abstract AliceResponse processRequest(AliceRequest request) throws ExchangeSkillException;
}
