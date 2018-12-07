package com.issart.alice.exchange.command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.google.common.collect.Sets;

public enum ExchangeCommand {

    GET_EXCHANGE,
    GET_CURRENCY,
    UNKNOWN;

    private static final Set<String> GET_EXCHANGE_WORDS = new HashSet<>(Arrays.asList("индекс"));
    private static final Set<String> GET_CURRENCY_WORDS = new HashSet<>(Arrays.asList("курс"));

    public static ExchangeCommand parseCommand(String textCommand) {
        Set<String> commands = new HashSet<>(Arrays.asList(textCommand.split(" ")));
        if(!Sets.intersection(GET_EXCHANGE_WORDS, commands).isEmpty()) {
            return GET_EXCHANGE;
        }
        if(!Sets.intersection(GET_CURRENCY_WORDS, commands).isEmpty()) {
            return GET_CURRENCY;
        }
        return UNKNOWN;
    }
}
