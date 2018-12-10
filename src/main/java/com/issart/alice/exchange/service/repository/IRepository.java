package com.issart.alice.exchange.service.repository;

import com.issart.alice.exchange.type.Exchange;
import com.issart.alice.exchange.type.ExchangeInfo;

public interface IRepository {

    void pull();
    ExchangeInfo getExchangeInfo(Exchange exchangeType);
}
