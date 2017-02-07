package com.stolser.search;

import com.stolser.controller.SearchParameters;

import java.util.List;

public interface IdSearcher {
    List<String> searchImdbIds(SearchParameters params);
}
