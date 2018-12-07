package com.issart.alice.wiffy.rest.dto.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.issart.alice.wiffy.rest.dto.request.Payload;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Button {

    @JsonProperty("title")
    private String              title;
    @JsonProperty("payload")
    private Payload             payload;
    @JsonProperty("url")
    private String              url;
    @JsonProperty("hide")
    private Boolean             hide;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("payload")
    public Payload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("hide")
    public Boolean getHide() {
        return hide;
    }

    @JsonProperty("hide")
    public void setHide(Boolean hide) {
        this.hide = hide;
    }

}