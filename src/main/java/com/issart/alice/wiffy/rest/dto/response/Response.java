package com.issart.alice.wiffy.rest.dto.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

    @JsonProperty("text")
    private String text;
    @JsonProperty("tts")
    private String tts;
    @JsonProperty("buttons")
    private List<Button> buttons = null;
    @JsonProperty("end_session")
    private Boolean endSession;

    public Response(String text) {
        this.text = text;
        this.tts = text;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("tts")
    public String getTts() {
        return tts;
    }

    @JsonProperty("tts")
    public void setTts(String tts) {
        this.tts = tts;
    }

    @JsonProperty("buttons")
    public List<Button> getButtons() {
        return buttons;
    }

    @JsonProperty("buttons")
    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    @JsonProperty("end_session")
    public Boolean getEndSession() {
        return endSession;
    }

    @JsonProperty("end_session")
    public void setEndSession(Boolean endSession) {
        this.endSession = endSession;
    }

}
