package com.issart.alice.wiffy.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.google.common.collect.Sets;

public enum WiffyCommand {

    SET_PASSWORD,
    GET_PASSWORD,
    UNKNOWN;

    private static final Set<String> GET_PASSWORD_WORDS = new HashSet<>(Arrays.asList("напомни",
        "скажи", "подскажи", "спросу", "узнай"));
    private static final Set<String> SET_PASSWORD_WORDS = new HashSet<>(Arrays.asList("задай",
        "установи", "запомни"));

    public static WiffyCommand parseCommand(String textCommand) {
        if(textCommand.contains("пароль")) {
            Set<String> commands = new HashSet<>(Arrays.asList(textCommand.split(" ")));
            if(!Sets.intersection(GET_PASSWORD_WORDS, commands).isEmpty()) {
                return GET_PASSWORD;
            }
            if(!Sets.intersection(SET_PASSWORD_WORDS, commands).isEmpty()) {
                return SET_PASSWORD;
            }

        }
        return UNKNOWN;
    }
}
