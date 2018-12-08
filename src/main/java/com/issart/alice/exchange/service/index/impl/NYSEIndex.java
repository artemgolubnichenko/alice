package com.issart.alice.exchange.service.index.impl;

import com.issart.alice.exchange.type.Index;

public class NYSEIndex extends BaseIndex {

    @Override
    public Index getExchangeCode() {
        return Index.NYSE;
    }
}
