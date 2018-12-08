package com.issart.alice.exchange;

import com.google.inject.Inject;
import com.issart.alice.common.Application;
import com.issart.alice.exchange.command.ExchangeCommand;
import com.issart.alice.exchange.exception.ExchangeSkillException;
import com.issart.alice.exchange.rest.dto.response.AliceReplicas;
import com.issart.alice.exchange.service.IExchangeCommandHandleService;
import com.issart.alice.exchange.type.Exchange;
import com.issart.alice.exchange.type.ExchangeInfo;
import com.issart.alice.rest.dto.request.AliceRequest;
import com.issart.alice.rest.dto.response.AliceResponse;
import com.issart.alice.rest.dto.response.Response;
import org.apache.log4j.Logger;

import static com.issart.alice.exchange.rest.dto.response.AliceReplicas.EXCHANGE_ANSWER_MSG;

public class ExchangeApplication extends Application {

    @Inject
    private IExchangeCommandHandleService service;
    private final static Logger LOGGER = Logger.getLogger(ExchangeApplication.class);

    @Override
    protected AliceResponse processRequest(AliceRequest request) throws ExchangeSkillException {
        AliceResponse response = new AliceResponse();
        if (newSession && (rawCommand.isEmpty()
            || request.getRequest().getNlu().getTokens().isEmpty())) {
            response.setResponse(new Response(AliceReplicas.INITIAL_MSG));
        } else {
            ExchangeCommand command = ExchangeCommand.parseCommand(rawCommand);
            Exchange type = ExchangeCommand.getExchangeType(command, rawCommand);
            ExchangeInfo info = service.handle(command, type);
            String change = info.getDiff() > 0 ? "вырос" : "упал";
            String text = String.format(EXCHANGE_ANSWER_MSG, command.getName(),
                info.getCurrent(), change, info.getDiff());
            response.setResponse(new Response(text));
            response.getResponse().setEndSession(true);
        }
        return response;
    }
}
