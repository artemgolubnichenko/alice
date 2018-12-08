package com.issart.alice.exchange.service.index.impl;

import com.issart.alice.exchange.type.Index;

public class NASDAQIndex extends BaseIndex {

    @Override
    public Index getExchangeCode() {
        return Index.NASDAQ;
    }
}
