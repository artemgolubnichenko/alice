package com.issart.alice.exchange.type;

public enum Currency {
    USD("доллар", "USD"),
    EUR("евро", "EUR"),
    GBP("фунт", "GBP");

    private String name;
    private String code;

    Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
