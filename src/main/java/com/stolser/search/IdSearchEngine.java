package com.stolser.search;

import com.stolser.controller.SearchParameters;

import java.util.List;

public interface IdSearchEngine {
    List<String> findVideoIds(SearchParameters params);
}
