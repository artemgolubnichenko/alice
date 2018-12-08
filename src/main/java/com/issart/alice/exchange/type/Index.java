package com.issart.alice.exchange.type;

import com.issart.alice.exchange.exception.ExchangeSkillException;
import com.issart.alice.exchange.rest.dto.response.AliceReplicas;
import org.apache.commons.lang3.StringUtils;

public enum Index implements Exchange {

    NYSE("Нью-Йоркская фондовая биржа", ""),
    NASDAQ("NASDAQ", "Nasdaq"), // https://www.rbc.ru/ajax/indicators
    LSE("Лондонская фондовая биржа", ""),
    IMOEX("Московская биржа", "IMOEX"), // https://www.rbc.ru/ajax/indicators
    RTSI("Российская торговая система", "RTSI"); // https://www.rbc.ru/ajax/indicators

    private String name;
    private String code;

    Index(String name, String code) {
        this.name = name;
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
        int min = 3;
        Index result = null;
        for(Index idx : indecies) {
            int dist = StringUtils.getLevenshteinDistance(idx.getName(), index);
            if(dist < min) {
                min = dist;
                result = idx;
            }
        }
        if(result != null && min <= 3) {
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
