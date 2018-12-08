package com.issart.alice.exchange.service.impl;

import com.issart.alice.exchange.command.ExchangeCommand;
import com.issart.alice.exchange.exception.ExchangeSkillException;
import com.issart.alice.exchange.rest.dto.response.AliceReplicas;
import com.issart.alice.exchange.service.IExchangeCommandHandleService;
import com.issart.alice.exchange.service.currency.impl.CurrencyFactory;
import com.issart.alice.exchange.service.index.impl.IndexFactory;
import com.issart.alice.exchange.type.Currency;
import com.issart.alice.exchange.type.Exchange;
import com.issart.alice.exchange.type.ExchangeInfo;
import com.issart.alice.exchange.type.Index;

public class ExchangeCommandHandleServiceImpl implements IExchangeCommandHandleService {

    @Override
    public ExchangeInfo handle(ExchangeCommand command, Exchange type) throws ExchangeSkillException {
        switch (command) {
            case GET_CURRENCY: {
                Currency currency = (Currency)type;
                return CurrencyFactory.getCurrency(currency).getCurrencyExchangeInfo();
            }
            case GET_INDEX: {
                Index index = (Index)type;
                return IndexFactory.getIndex(index).getIndexExchangeInfo();
            }
            case UNKNOWN: {
                throw new ExchangeSkillException(AliceReplicas.EXCEPTION_MSG);
            }
        }
        return null;
    }
}
