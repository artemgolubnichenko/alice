package com.issart.alice.exchange.type;

public enum Exchange {

    NYSE("Нью-Йоркская фондовая биржа"),
    NASDAQ("NASDAQ"),
    LSE("Лондонская фондовая биржа"),
    MMVB("Московская биржа"),
    RTS("Российская торговая система");

    private String name;

    Exchange(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
