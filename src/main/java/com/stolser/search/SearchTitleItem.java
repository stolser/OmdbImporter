package com.stolser.search;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchTitleItem {
    @JsonProperty("imdbID")
    private String imdbId;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
}
