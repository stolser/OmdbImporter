package com.stolser.search;

import com.stolser.controller.SearchParameters;

import java.util.List;

public interface IdSearcher {
    /**
     * Retrieves from the API imdbIds of all videos that meet specified search parameters.
     */
    List<String> searchImdbIds(SearchParameters params);
}
