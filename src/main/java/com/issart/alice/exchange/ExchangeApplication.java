package com.issart.alice.exchange;

import com.issart.alice.common.Application;
import com.issart.alice.exchange.rest.dto.response.AliceReplicas;
import com.issart.alice.rest.dto.request.AliceRequest;
import com.issart.alice.rest.dto.response.AliceResponse;
import com.issart.alice.rest.dto.response.Response;
import org.apache.log4j.Logger;

public class ExchangeApplication extends Application {

    private final static Logger LOGGER = Logger.getLogger(ExchangeApplication.class);

    @Override
    protected AliceResponse processRequest(AliceRequest request) {
        AliceResponse response = new AliceResponse();
        if (newSession && (rawCommand.isEmpty()
            || request.getRequest().getNlu().getEntities().isEmpty())) {
            response.setResponse(new Response(AliceReplicas.INITIAL_MSG));
        } else {

        }
        return response;
    }
}
