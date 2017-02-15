package com.stolser.entity;

public enum VideoMpaaRating {
    G("g.shortDescription", "g.longDescription"),
    PG("pg.shortDescription", "pg.longDescription"),
    PG_13("pg_13.shortDescription", "pg_13.longDescription"),
    TV_14("tv_14.shortDescription", "tv_14.longDescription"),
    TV_MA("tv_14.shortDescription", "tv_14.shortDescription"),
    APPROVED("tv_14.shortDescription", "tv_14.shortDescription"),
    R("r.shortDescription", "r.longDescription"),
    X("x.shortDescription", "x.longDescription"),
    NC_17("nc_17.shortDescription", "nc_17.longDescription"),
    NOT_RATED("not_rated.shortDescription", "not_rated.longDescription"),
    UNRATED("not_rated.shortDescription", "not_rated.longDescription");

    private String shortDescription;
    private String longDescription;

    VideoMpaaRating(String shortDescription, String longDescription) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }
}
