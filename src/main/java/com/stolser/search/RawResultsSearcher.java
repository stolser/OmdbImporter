package com.stolser.search;

import java.net.URI;

public interface RawResultsSearcher {
    /**
     * Makes a request to the API and returns an object representing a returned raw data.
     */
    <T> T searchRawResults(URI searchUri, Class<T> resultsClass);
}
