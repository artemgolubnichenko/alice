package com.issart.alice.exchange.service.currency.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;
import com.issart.alice.exchange.service.currency.ICurrency;
import com.issart.alice.exchange.service.currency.rest.dto.response.CurrencyResponse;
import com.issart.alice.exchange.service.currency.rest.dto.response.Valute;
import com.issart.alice.exchange.type.Currency;
import com.issart.alice.exchange.type.ExchangeInfo;
import io.vertx.core.json.Json;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class BaseCurrency implements ICurrency {

    protected Map<Currency, ExchangeInfo> currencyInfoMap = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public static final String RESOURCE_URL = "https://www.cbr-xml-daily.ru/daily_json.js";
    private final static Logger LOGGER = Logger.getLogger(BaseCurrency.class);

    public BaseCurrency() {
        scheduler.scheduleAtFixedRate(() -> {
            pull();
            LOGGER.info("Pulling for currencies..");
        }, 0, 30, MINUTES);
    }

    @Override
    public ExchangeInfo getCurrencyExchangeInfo() {
        return currencyInfoMap.get(getCurrencyCode());
    }

    public abstract Currency getCurrencyCode();

    private void pull() {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(RESOURCE_URL);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            CurrencyResponse response = Json.decodeValue(json, CurrencyResponse.class);
            Map<String, Valute> map = response.getValutes();
            Set<String> availableCarriencies = new HashSet<>(Arrays.asList(Currency.values()))
                .stream()
                .map(Currency::getCode)
                .collect(Collectors.toSet());
            for (Map.Entry<String, Valute> entry : map.entrySet())
            {
                if(availableCarriencies.contains(entry.getKey())) {
                    Valute valute = entry.getValue();
                    ExchangeInfo info = new ExchangeInfo(valute.getValue(), valute.getPrevious());
                    currencyInfoMap.put(Currency.valueOf(entry.getKey()), info);
                }
            }
        } catch (IOException ex) {
            LOGGER.error("Can't pull currencies: " + ex.getMessage(), ex);
        }
    }
}
