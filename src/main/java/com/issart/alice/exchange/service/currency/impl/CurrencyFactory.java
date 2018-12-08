package com.issart.alice.exchange.service.currency.impl;

import com.issart.alice.exchange.type.Currency;

public class CurrencyFactory {

    public static BaseCurrency getCurrency(Currency currency) {
        switch (currency) {
            case EUR:
                return new EURCurrency();
            case USD:
                return new USDCurrency();
            case GBP:
                return new GBPCurrency();
        }
        throw new RuntimeException("Не поддерживаемая валюта");
    }

}
