package com.issart.alice.exchange.service.currency.impl;

import com.issart.alice.exchange.type.Currency;

public class GBPCurrency extends BaseCurrency {

    @Override
    public Currency getCurrencyCode() {
        return Currency.GBP;
    }
}
