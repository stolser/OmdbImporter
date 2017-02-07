package com.stolser.search;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;
import java.util.stream.Collectors;

class MultiVideoResult extends SearchResult {
    @JsonProperty("totalResults")
    private int totalResults;

    private List<SearchItem> items;

    List<String> getImdbIds() {
        return items.stream().map(SearchItem::getImdbId).collect(Collectors.toList());
    }

    int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    @JsonGetter("items")
    public List<SearchItem> getItems() {
        return items;
    }

    @JsonSetter("Search")
    public void setSearchItems(List<SearchItem> items) {
        this.items = items;
    }

    @JsonSetter("Episodes")
    private void setEpisodeItems(List<SearchItem> items) {
        this.items = items;
    }
}
