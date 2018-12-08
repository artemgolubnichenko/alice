package com.issart.alice.exchange.rest.dto.response;

public class AliceReplicas {

    public static final String SKILL_NAME = "Биржа";
    public static final String INITIAL_MSG = "Для работы с навыком используй команды: " +
        "какой индекс у РТС или какой курс доллара";
    public static final String EXCEPTION_MSG = "Неизвестная команда. Попробуйте спросить " +
        "какой индекс у РТС или какой курс доллара";
    public static final String EXCEPTION_INDEX_MSG = "Неизвестная биржа. Попробуйте еще раз спросить";
    public static final String EXCEPTION_CURRENCY_MSG = "Неизвестная валюта. Попробуйте еще раз спросить";
    public static final String EXCHANGE_ANSWER_MSG = "%s %s, %s на %s";
}
