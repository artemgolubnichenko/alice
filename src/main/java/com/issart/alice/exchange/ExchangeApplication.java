package com.issart.alice.exchange;

import com.issart.alice.rest.dto.request.AliceRequest;
import com.issart.alice.rest.dto.response.AliceResponse;
import org.apache.log4j.Logger;

public class ExchangeApplication {

    private final static Logger LOGGER = Logger.getLogger(ExchangeApplication.class);

    public AliceResponse handleAliceRequest(AliceRequest request) {
        AliceResponse response = new AliceResponse();

        response.setSession(request.getSession());
        response.getSession().setNew(null);
        response.getSession().setSkillId(null);
        response.setVersion(request.getVersion());
        return response;
    }
}
