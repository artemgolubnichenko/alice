package com.issart.alice.exchange.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.common.collect.Sets;
import com.issart.alice.exchange.exception.ExchangeSkillException;
import com.issart.alice.exchange.type.Currency;
import com.issart.alice.exchange.type.Exchange;
import com.issart.alice.exchange.type.Index;

import static com.issart.alice.exchange.rest.dto.response.AliceReplicas.EXCEPTION_CURRENCY_MSG;
import static com.issart.alice.exchange.rest.dto.response.AliceReplicas.EXCEPTION_INDEX_MSG;

public enum ExchangeCommand {

    GET_INDEX("Индекс"),
    GET_CURRENCY("Курс"),
    UNKNOWN("");

    ExchangeCommand(String name) {
        this.name = name;
    }

    private String name;
    private static final Set<String> GET_INDEX_WORDS    = new HashSet<>(Arrays.asList("индекс"));
    private static final Set<String> GET_CURRENCY_WORDS = new HashSet<>(Arrays.asList("курс"));

    public static ExchangeCommand parseCommand(String textCommand) throws ExchangeSkillException {
        Set<String> commands = new HashSet<>(Arrays.asList(textCommand.split(" ")));
        if(!Sets.intersection(GET_INDEX_WORDS, commands).isEmpty()) {
            return GET_INDEX;
        }
        if(!Sets.intersection(GET_CURRENCY_WORDS, commands).isEmpty()) {
            return GET_CURRENCY;
        }
        return UNKNOWN;
    }

    public static Exchange getExchangeType(ExchangeCommand command, String textCommand) throws ExchangeSkillException {
        List<String> commands = new ArrayList<>(Arrays.asList(textCommand.split(" ")));
        if(command == GET_CURRENCY) {
            List<String> candidates = commands.stream()
                .filter(s -> !GET_CURRENCY_WORDS.contains(s))
                .filter(s -> s.length() > 2)
                .collect(Collectors.toList());
            for(String candidate : candidates) {
                try {
                    return Currency.getLevenshteinCurrency(candidate);
                } catch (IllegalArgumentException ex) {
                    // don't need to handle it
                }
            }
        } else if(command == GET_INDEX) {
            String candidate = commands.stream()
                .filter(s -> !GET_INDEX_WORDS.contains(s))
                .filter(s -> !s.equalsIgnoreCase("какой"))
                .filter(s -> s.length() > 2)
                .collect(Collectors.joining(""));
            try {
                return Index.getLevenshteinIndex(candidate);
            } catch (IllegalArgumentException ex) {
                // don't need to handle it
            }
        }
        throw new ExchangeSkillException(command == GET_INDEX ?
            EXCEPTION_INDEX_MSG : EXCEPTION_CURRENCY_MSG);
    }

    public String getName() {
        return name;
    }
}
