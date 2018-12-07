package com.issart.alice.exchange.service.currency.impl;

import com.issart.alice.exchange.type.Currency;

public class EURCurrency extends BaseCurrency {

    @Override
    public Currency getCurrencyCode() {
        return Currency.EUR;
    }
}
