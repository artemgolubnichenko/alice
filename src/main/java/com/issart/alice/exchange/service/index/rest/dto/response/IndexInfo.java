package com.issart.alice.exchange.service.index.rest.dto.response;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndexInfo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("subname")
    private String subname;
    @JsonProperty("url")
    private String url;
    @JsonProperty("time")
    private Date time;
    @JsonProperty("date")
    private String date;
    @JsonProperty("color")
    private String color;
    @JsonProperty("chg_abs")
    private Double chgAbs;
    @JsonProperty("chg_percent")
    private Double chgPercent;
    @JsonProperty("diff_high")
    private Double diffHigh;
    @JsonProperty("diff_low")
    private Double diffLow;
    @JsonProperty("high")
    private String high;
    @JsonProperty("low")
    private String low;
    @JsonProperty("value1")
    private String value1;
    @JsonProperty("value2")
    private String value2;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("subname")
    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty("chg_abs")
    public Double getChgAbs() {
        return chgAbs;
    }

    public void setChgAbs(Double chgAbs) {
        this.chgAbs = chgAbs;
    }

    @JsonProperty("chg_percent")
    public Double getChgPercent() {
        return chgPercent;
    }

    public void setChgPercent(Double chgPercent) {
        this.chgPercent = chgPercent;
    }

    @JsonProperty("diff_high")
    public Double getDiffHigh() {
        return diffHigh;
    }

    public void setDiffHigh(Double diffHigh) {
        this.diffHigh = diffHigh;
    }

    @JsonProperty("diff_low")
    public Double getDiffLow() {
        return diffLow;
    }

    public void setDiffLow(Double diffLow) {
        this.diffLow = diffLow;
    }

    @JsonProperty("high")
    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    @JsonProperty("low")
    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    @JsonProperty("value1")
    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    @JsonProperty("value2")
    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }
}
