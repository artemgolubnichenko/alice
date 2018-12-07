package com.issart.alice.exchange.service.currency.impl;

import com.issart.alice.exchange.type.Currency;

public class USDCurrency extends BaseCurrency {

    @Override
    public Currency getCurrencyCode() {
        return Currency.USD;
    }
}
