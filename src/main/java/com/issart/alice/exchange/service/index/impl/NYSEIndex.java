package com.issart.alice.exchange.service.index.impl;

import com.issart.alice.exchange.type.Exchange;

public class NYSEIndex extends BaseIndex {

    @Override
    public Exchange getExchangeCode() {
        return Exchange.NYSE;
    }
}
