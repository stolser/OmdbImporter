package com.stolser.controller;

import com.stolser.entity.Video;

import javax.validation.constraints.NotNull;

public class SearchParameters {
    @NotNull
    private String searchText;
    private int searchYear;
    @NotNull
    private Video.Type searchVideoType;

    public SearchParameters() {}

    public SearchParameters(String searchText, int searchYear, Video.Type searchVideoType) {
        this.searchText = searchText;
        this.searchYear = searchYear;
        this.searchVideoType = searchVideoType;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public int getSearchYear() {
        return searchYear;
    }

    public void setSearchYear(int searchYear) {
        this.searchYear = searchYear;
    }

    public Video.Type getSearchVideoType() {
        return searchVideoType;
    }

    public void setSearchVideoType(Video.Type searchVideoType) {
        this.searchVideoType = searchVideoType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SearchParameters that = (SearchParameters) o;

        if (searchYear != that.searchYear) {
            return false;
        }
        if (searchText != null ? !searchText.equals(that.searchText) : that.searchText != null) {
            return false;
        }
        return searchVideoType == that.searchVideoType;

    }

    @Override
    public int hashCode() {
        int result = searchText != null ? searchText.hashCode() : 0;
        result = 31 * result + searchYear;
        result = 31 * result + (searchVideoType != null ? searchVideoType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("SearchParameters{searchText='%s', searchYear=%d, searchVideoType=%s}",
                searchText, searchYear, searchVideoType);
    }
}
