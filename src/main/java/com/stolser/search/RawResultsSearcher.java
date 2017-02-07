package com.stolser.search;

import java.net.URI;

public interface RawResultsSearcher {
    <T> T searchRawResults(URI searchUri, Class<T> resultsClass);
}
