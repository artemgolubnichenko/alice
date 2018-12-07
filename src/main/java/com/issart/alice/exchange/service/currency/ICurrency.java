package com.issart.alice.exchange.service.currency;

import com.issart.alice.exchange.type.Currency;
import com.issart.alice.exchange.type.ExchangeInfo;

public interface ICurrency {

    ExchangeInfo getCurrency(Currency currency);
}
