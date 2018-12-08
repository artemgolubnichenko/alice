package com.issart.alice.exchange.service.index.rest.dto.response;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndexResponse {

    @JsonProperty("cash")
    private List<IndexInfo> cashes;
    @JsonProperty("currency")
    private List<IndexInfo> currencies;
    @JsonProperty("indices")
    private List<IndexInfo> indices;

    @JsonProperty("cash")
    public List<IndexInfo> getCashes() {
        return cashes;
    }

    public void setCashes(List<IndexInfo> cashes) {
        this.cashes = cashes;
    }

    @JsonProperty("currency")
    public List<IndexInfo> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<IndexInfo> currencies) {
        this.currencies = currencies;
    }

    @JsonProperty("indices")
    public List<IndexInfo> getIndices() {
        return indices;
    }

    public void setIndices(List<IndexInfo> indices) {
        this.indices = indices;
    }
}