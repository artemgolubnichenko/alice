package com.issart.alice.exchange.type;

import java.math.BigDecimal;

public class ExchangeInfo {

    private float current;
    private float prev;
    private String currency;
    private float percent;

    public ExchangeInfo(float current, float prev, String currency, float percent) {
        this.current = current;
        this.prev = prev;
        this.currency = currency;
        this.percent = percent;
    }

    public float getCurrent() {
        return round(current);
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getPrev() {
        return round(prev);
    }

    public void setPrev(float prev) {
        this.prev = prev;
    }

    public float getDiff() {
        return round(current-prev);
    }

    private float round(float value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
