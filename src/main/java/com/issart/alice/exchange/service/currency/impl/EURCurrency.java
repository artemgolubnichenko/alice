package com.issart.alice.exchange.service.currency.impl;

import com.issart.alice.exchange.type.Currency;
import com.issart.alice.exchange.type.ExchangeInfo;

public class EURCurrency extends BaseCurrency {

    @Override
    public Currency getCurrencyCode() {
        return Currency.EUR;
    }

    public static void main(String[] args) {
        EURCurrency currency = new EURCurrency();
        ExchangeInfo info = currency.getCurrencyExchangeInfo();
        System.out.println("cur: " + info.getCurrent());
        System.out.println("pre: " + info.getPrev());
        System.out.println("dif: " + info.getDiff());
    }
}
