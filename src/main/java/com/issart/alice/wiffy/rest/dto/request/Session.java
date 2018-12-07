package com.issart.alice.wiffy.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {

    @JsonProperty("message_id")
    private Integer messageId;
    @JsonProperty("new")
    private Boolean _new;
    @JsonProperty("session_id")
    private String sessionId;
    @JsonProperty("skill_id")
    private String skillId;
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("message_id")
    public Integer getMessageId() {
        return messageId;
    }

    @JsonProperty("message_id")
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    @JsonProperty("new")
    public Boolean getNew() {
        return _new;
    }

    @JsonProperty("new")
    public void setNew(Boolean _new) {
        this._new = _new;
    }

    @JsonProperty("session_id")
    public String getSessionId() {
        return sessionId;
    }

    @JsonProperty("session_id")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @JsonProperty("skill_id")
    public String getSkillId() {
        return skillId;
    }

    @JsonProperty("skill_id")
    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
