package com.issart.alice.exchange.type;

public enum Exchange {

    NYSE("Нью-Йоркская фондовая биржа", ""),
    NASDAQ("NASDAQ", "Nasdaq"), // https://www.rbc.ru/ajax/indicators
    LSE("Лондонская фондовая биржа", ""),
    IMOEX("Московская биржа", "IMOEX"), // https://www.rbc.ru/ajax/indicators
    RTSI("Российская торговая система", "RTSI"); // https://www.rbc.ru/ajax/indicators

    private String name;
    private String code;

    Exchange(String name, String code) {
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
