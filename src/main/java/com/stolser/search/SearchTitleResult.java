package com.stolser.search;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class SearchTitleResult extends SearchResult {
    @JsonProperty("totalResults")
    private int totalResults;
    @JsonProperty("Search")
    private List<SearchTitleItem> items;

    public List<String> getImdbIds() {
        return items.stream().map(SearchTitleItem::getImdbId).collect(Collectors.toList());
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<SearchTitleItem> getItems() {
        return items;
    }

    public void setItems(List<SearchTitleItem> items) {
        this.items = items;
    }
}
