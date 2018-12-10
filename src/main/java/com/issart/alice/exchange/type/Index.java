package com.issart.alice.exchange.type;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public enum Index implements Exchange {

    NYSE("Нью-Йоркская фондовая биржа", Arrays.asList("нью-йоркскаяфондоваябиржа"), "^NYA"), // YahooFinance
    NASDAQ("NASDAQ", Arrays.asList("насдак"), "Nasdaq"), // https://www.rbc.ru/ajax/indicators
    LSE("Лондонская фондовая биржа", Arrays.asList("лондонскаяфондоваябиржа"), "LSE.L"), // YahooFinance
    IMOEX("Московская биржа", Arrays.asList("московскаябиржа", "биржамосковская"), "IMOEX"), // https://www.rbc.ru/ajax/indicators
    RTSI("Российская торговая система", Arrays.asList("российскаяторговаясистема", "ртс"), "RTSI"); // https://www.rbc.ru/ajax/indicators

    private String name;
    private List<String> synonyms;
    private String code;

    Index(String name, List<String> synonyms, String code) {
        this.name = name;
        this.synonyms = synonyms;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static Index parseCode(String code) {
        Index[] values = values();
        for(Index value : values) {
            if (value.getCode().equalsIgnoreCase(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Can't parse index with code: " + code);
    }

    public static Index getLevenshteinIndex(String index) {
        Index[] indecies = values();
        int min = 8;
        Index result = null;
        for(Index idx : indecies) {
            for(String synonym : idx.synonyms) {
                int dist = StringUtils.getLevenshteinDistance(synonym, index);
                if (dist < min) {
                    min = dist;
                    result = idx;
                }
            }
        }
        if(result != null && min < 7) {
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
