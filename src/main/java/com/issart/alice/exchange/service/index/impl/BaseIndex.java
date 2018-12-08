package com.issart.alice.exchange.service.index.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;
import com.issart.alice.exchange.service.currency.impl.BaseCurrency;
import com.issart.alice.exchange.service.index.IIndex;
import com.issart.alice.exchange.service.index.rest.dto.response.IndexInfo;
import com.issart.alice.exchange.service.index.rest.dto.response.IndexResponse;
import com.issart.alice.exchange.type.Exchange;
import com.issart.alice.exchange.type.ExchangeInfo;
import io.vertx.core.json.Json;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import static java.util.concurrent.TimeUnit.MINUTES;

public abstract class BaseIndex implements IIndex {

    protected Map<Exchange, ExchangeInfo> exchangeInfoMap = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public static final  String RESOURCE_URL = "https://www.rbc.ru/ajax/indicators";
    private final static Logger LOGGER = Logger.getLogger(BaseCurrency.class);

    public BaseIndex() {
        scheduler.scheduleAtFixedRate(() -> {
            pull();
            LOGGER.info("Pulling for currencies..");
        }, 0, 30, MINUTES);
    }


    @Override
    public ExchangeInfo getIndex() {
        return exchangeInfoMap.get(getExchangeCode());
    }

    public abstract Exchange getExchangeCode();

    private void pull() {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(RESOURCE_URL);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            IndexResponse response = Json.decodeValue(json, IndexResponse.class);
            List<IndexInfo> indexInfoList = response.getIndices();
            Set<String> availableIndices = new HashSet<>(Arrays.asList(Exchange.values()))
                .stream()
                .map(Exchange::getCode)
                .collect(Collectors.toSet());
            indexInfoList.forEach(indexInfo -> {
                if(availableIndices.contains(indexInfo.getName())) {
                    float curVal = Float.parseFloat(indexInfo.getValue1());
                    ExchangeInfo info = new ExchangeInfo(curVal,
                        curVal - indexInfo.getChgAbs().floatValue());
                    exchangeInfoMap.put(Exchange.parseCode(indexInfo.getName()), info);
                }
            });
        } catch (IOException ex) {
            LOGGER.error("Can't pull indices: " + ex.getMessage(), ex);
        }
    }
}
