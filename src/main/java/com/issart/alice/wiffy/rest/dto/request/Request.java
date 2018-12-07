package com.issart.alice.wiffy.rest.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request {

    @JsonProperty("command")
    private String command = "";
    @JsonProperty("original_utterance")
    private String originalUtterance;
    @JsonProperty("type")
    private String type;
    @JsonProperty("markup")
    private Markup markup;
    @JsonProperty("payload")
    private Payload payload;
    @JsonProperty("nlu")
    private Nlu nlu;

    @JsonProperty("command")
    public String getCommand() {
        return command;
    }

    @JsonProperty("command")
    public void setCommand(String command) {
        this.command = command;
    }

    @JsonProperty("original_utterance")
    public String getOriginalUtterance() {
        return originalUtterance;
    }

    @JsonProperty("original_utterance")
    public void setOriginalUtterance(String originalUtterance) {
        this.originalUtterance = originalUtterance;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("markup")
    public Markup getMarkup() {
        return markup;
    }

    @JsonProperty("markup")
    public void setMarkup(Markup markup) {
        this.markup = markup;
    }

    @JsonProperty("payload")
    public Payload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @JsonProperty("nlu")
    public Nlu getNlu() {
        return nlu;
    }

    @JsonProperty("nlu")
    public void setNlu(Nlu nlu) {
        this.nlu = nlu;
    }
}