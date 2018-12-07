package com.issart.alice.common;

import com.issart.alice.rest.dto.request.AliceRequest;
import com.issart.alice.rest.dto.response.AliceResponse;

public abstract class Application {

    protected boolean newSession;
    protected String rawCommand;

    public AliceResponse handleAliceRequest(AliceRequest request) {
        newSession = request.getSession().getNew();
        rawCommand = request.getRequest().getCommand();

        AliceResponse response = processRequest(request);
        response.setSession(request.getSession());
        response.getSession().setNew(null);
        response.getSession().setSkillId(null);
        response.setVersion(request.getVersion());
        return response;
    }

    protected abstract AliceResponse processRequest(AliceRequest request);
}
