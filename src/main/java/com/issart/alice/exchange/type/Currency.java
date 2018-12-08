package com.issart.alice.exchange.type;

import com.issart.alice.exchange.exception.ExchangeSkillException;
import com.issart.alice.exchange.rest.dto.response.AliceReplicas;
import org.apache.commons.lang3.StringUtils;

public enum Currency implements Exchange {
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

    public static Currency getLevenshteinCurrency(String currency)  {
        Currency[] currencies = values();
        int min = 3;
        Currency result = null;
        for(Currency cur : currencies) {
            int dist = StringUtils.getLevenshteinDistance(cur.getName(), currency);
            if(dist < min) {
                min = dist;
                result = cur;
            }
        }
        if(result != null && min <= 3) {
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
