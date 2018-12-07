package com.issart.alice.exchange.type;

import java.math.BigDecimal;

public class ExchangeInfo {

    private float current;
    private float prev;

    public ExchangeInfo(float current, float prev) {
        this.current = current;
        this.prev = prev;
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
}
