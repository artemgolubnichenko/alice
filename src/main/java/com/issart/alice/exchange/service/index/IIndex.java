package com.issart.alice.exchange.service.index;

import com.issart.alice.exchange.type.Exchange;
import com.issart.alice.exchange.type.ExchangeInfo;

public interface IIndex {

    ExchangeInfo getIndex(Exchange exchange);
}
