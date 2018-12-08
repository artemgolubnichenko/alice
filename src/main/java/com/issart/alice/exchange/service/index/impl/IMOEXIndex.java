package com.issart.alice.exchange.service.index.impl;

import com.issart.alice.exchange.type.Index;

public class IMOEXIndex extends BaseIndex {

    @Override
    public Index getExchangeCode() {
        return Index.IMOEX;
    }
}
