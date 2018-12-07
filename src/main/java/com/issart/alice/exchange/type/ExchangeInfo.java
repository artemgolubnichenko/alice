package com.issart.alice.exchange.type;

public class ExchangeInfo {

    private float current;
    private float prev;

    public ExchangeInfo(float current, float prev) {
        this.current = current;
        this.prev = prev;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getPrev() {
        return prev;
    }

    public void setPrev(float prev) {
        this.prev = prev;
    }

    public float getDiff() {
        return current-prev;
    }
}
