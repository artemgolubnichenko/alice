package com.issart.alice.exchange.service.currency.rest.dto.response;

import java.util.Date;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyResponse {

    @JsonProperty("Date")
    private Date date;
    @JsonProperty("PreviousDate")
    private Date previousDate;
    @JsonProperty("Timestamp")
    private Date timestamp;

    @JsonProperty("Valute")
    private Map<String, Valute> valutes;

    @JsonProperty("Date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonProperty("PreviousDate")
    public Date getPreviousDate() {
        return previousDate;
    }

    public void setPreviousDate(Date previousDate) {
        this.previousDate = previousDate;
    }

    @JsonProperty("Timestamp")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("Valute")
    public Map<String, Valute> getValutes() {
        return valutes;
    }

    public void setValutes(Map<String, Valute> valutes) {
        this.valutes = valutes;
    }
}