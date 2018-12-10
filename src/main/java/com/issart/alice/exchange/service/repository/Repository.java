package com.issart.alice.exchange.service.repository;

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
import com.issart.alice.exchange.service.currency.rest.dto.response.CurrencyResponse;
import com.issart.alice.exchange.service.currency.rest.dto.response.Valute;
import com.issart.alice.exchange.service.index.rest.dto.response.IndexInfo;
import com.issart.alice.exchange.service.index.rest.dto.response.IndexResponse;
import com.issart.alice.exchange.type.Currency;
import com.issart.alice.exchange.type.Exchange;
import com.issart.alice.exchange.type.ExchangeInfo;
import com.issart.alice.exchange.type.Index;
import io.vertx.core.json.Json;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import static com.issart.alice.exchange.type.Index.LSE;
import static com.issart.alice.exchange.type.Index.NYSE;

public class Repository implements IRepository {

    protected Map<Currency, ExchangeInfo> currencyInfoMap = new HashMap<>();
    protected Map<Index, ExchangeInfo> exchangeInfoMap = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public static final String RESOURCE_URL_CURRENCY = "https://www.cbr-xml-daily.ru/daily_json.js";
    public static final  String RESOURCE_URL_INDEX = "https://www.rbc.ru/ajax/indicators";
    private final static Logger LOGGER = Logger.getLogger(Repository.class);

    @Override
    public void pull() {
        pullCurrencies();
        pullIndecies();
    }

    @Override
    public ExchangeInfo getExchangeInfo(Exchange exchangeType) {
        if(exchangeType instanceof Currency) {
            return currencyInfoMap.get(exchangeType);
        }
        if(exchangeType instanceof Index) {
            return exchangeInfoMap.get(exchangeType);
        }
        return null;
    }

    private void pullCurrencies() {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(RESOURCE_URL_CURRENCY);
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
                    ExchangeInfo info = new ExchangeInfo(valute.getValue(), valute.getPrevious(), "рублей", 0);
                    currencyInfoMap.put(Currency.valueOf(entry.getKey()), info);
                }
            }
        } catch (IOException ex) {
            LOGGER.error("Can't pull currencies: " + ex.getMessage(), ex);
        }
    }

    private void pullIndecies() {
        // NASDAQ, IMOEX, RTSI
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(RESOURCE_URL_INDEX);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            IndexResponse response = Json.decodeValue(json, IndexResponse.class);
            List<IndexInfo> indexInfoList = response.getIndices();
            Set<String> availableIndices = new HashSet<>(Arrays.asList(Index.values()))
                .stream()
                .map(Index::getCode)
                .collect(Collectors.toSet());
            indexInfoList.forEach(indexInfo -> {
                if(availableIndices.contains(indexInfo.getName())) {
                    float curVal = Float.parseFloat(indexInfo.getValue1());
                    ExchangeInfo info = new ExchangeInfo(curVal,
                        curVal - indexInfo.getChgAbs().floatValue(), "пунктов", indexInfo.getChgPercent().floatValue());
                    exchangeInfoMap.put(Index.parseCode(indexInfo.getName()), info);
                }
            });
        } catch (IOException ex) {
            LOGGER.error("Can't pull indices from RBK: " + ex.getMessage(), ex);
        }
        // NYSE, LSE
        List<Index> yahooFinanceStocks = Arrays.asList(NYSE, LSE);
        yahooFinanceStocks.forEach(index -> {
            try {
                Stock stock = YahooFinance.get(index.getCode());
                float price = stock.getQuote(true).getPrice().floatValue();
                float change = stock.getQuote(true).getChange().floatValue();
                float percent = stock.getQuote(true).getChangeInPercent().floatValue();
                ExchangeInfo info = new ExchangeInfo(price, price-change, "пунктов", percent);
                exchangeInfoMap.put(index, info);
            } catch (IOException ex) {
                LOGGER.error("Can't pull indices from YahooFinance: " + ex.getMessage(), ex);
            }
        });
    }
}
