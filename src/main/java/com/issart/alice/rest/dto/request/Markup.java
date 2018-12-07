package com.issart.alice.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Markup {

    @JsonProperty("dangerous_context")
    private Boolean dangerousContext;

    @JsonProperty("dangerous_context")
    public Boolean getDangerousContext() {
        return dangerousContext;
    }

    @JsonProperty("dangerous_context")
    public void setDangerousContext(Boolean dangerousContext) {
        this.dangerousContext = dangerousContext;
    }

}
