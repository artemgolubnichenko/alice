package com.issart.alice.exchange.type;

public class ExchangeInfo {

    private float current;
    private float prev;
    private float diff;

    public ExchangeInfo(float current, float prev, float diff) {
        this.current = current;
        this.prev = prev;
        this.diff = diff;
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
        return diff;
    }

    public void setDiff(float diff) {
        this.diff = diff;
    }
}
