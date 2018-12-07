package com.issart.alice.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.issart.alice.rest.dto.request.Session;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AliceResponse {

    @JsonProperty("response")
    private Response response;
    @JsonProperty("session")
    private Session session;
    @JsonProperty("version")
    private String version;

    @JsonProperty("response")
    public Response getResponse() {
        return response;
    }

    @JsonProperty("response")
    public void setResponse(Response response) {
        this.response = response;
    }

    @JsonProperty("session")
    public Session getSession() {
        return session;
    }

    @JsonProperty("session")
    public void setSession(Session session) {
        this.session = session;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }
}
