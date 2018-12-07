package com.issart.alice.exchange.type;

public enum Currency {
    USD("доллар"),
    EUR("евро"),
    GBP("фунт");

    private String name;

    Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
