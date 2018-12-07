package com.issart.alice.exchange;

import com.issart.alice.common.Application;
import com.issart.alice.rest.dto.request.AliceRequest;
import com.issart.alice.rest.dto.response.AliceResponse;
import org.apache.log4j.Logger;

public class ExchangeApplication extends Application {

    private final static Logger LOGGER = Logger.getLogger(ExchangeApplication.class);

    @Override
    protected AliceResponse processRequest(AliceRequest request) {
        AliceResponse response = new AliceResponse();
        return response;
    }
}
