package com.stolser.entity;

public enum VideoType {
    MOVIE("formPage.movie.label"),
    SERIES("formPage.series.label"),
    EPISODE("formPage.episode.label");

    private String messageKey;

    VideoType(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
