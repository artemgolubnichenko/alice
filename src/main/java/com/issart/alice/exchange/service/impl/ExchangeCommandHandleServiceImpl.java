package com.issart.alice.exchange.service.impl;

import com.google.inject.Inject;
import com.issart.alice.exchange.command.ExchangeCommand;
import com.issart.alice.exchange.exception.ExchangeSkillException;
import com.issart.alice.exchange.rest.dto.response.AliceReplicas;
import com.issart.alice.exchange.service.IExchangeCommandHandleService;
import com.issart.alice.exchange.service.repository.IRepository;
import com.issart.alice.exchange.type.Currency;
import com.issart.alice.exchange.type.Exchange;
import com.issart.alice.exchange.type.ExchangeInfo;
import com.issart.alice.exchange.type.Index;

public class ExchangeCommandHandleServiceImpl implements IExchangeCommandHandleService {

    @Inject
    private IRepository repository;

    @Override
    public ExchangeInfo handle(ExchangeCommand command, Exchange type) throws ExchangeSkillException {
        switch (command) {
            case GET_CURRENCY: {
                Currency currency = (Currency)type;
                return repository.getExchangeInfo(currency);
            }
            case GET_INDEX: {
                Index index = (Index)type;
                return repository.getExchangeInfo(index);
            }
            case UNKNOWN: {
                throw new ExchangeSkillException(AliceReplicas.EXCEPTION_MSG);
            }
        }
        return null;
    }
}
