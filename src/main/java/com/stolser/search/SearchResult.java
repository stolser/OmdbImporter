package com.stolser.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SearchResult {
    @JsonProperty("Response")
    private boolean isSuccess;
    @JsonProperty("Error")
    private String errorMsg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
