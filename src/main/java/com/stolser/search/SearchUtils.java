package com.stolser.search;

import com.stolser.controller.SearchParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.TimeUnit;

class SearchUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchUtils.class);
    private static final String BASIC_OMDBAPI_URL = "http://www.omdbapi.com/";
    private static final String SEARCH_PARAM = "s";
    private static final String TYPE_PARAM = "type";
    private static final String YEAR_PARAM = "y";
    private static final int SEARCH_REQUEST_ATTEMPTS_MAX = 2;
    private static final int WAIT_TIME_BEFORE_RETRY = 2;

    @Autowired
    private RestTemplate restTemplate;

    SearchTitleResult getSearchTitleResult(URI searchUri) {
        ResponseEntity<SearchTitleResult> responseEntity;
        int attemptNumber = 0;

        do {
            checkTimeout(attemptNumber, searchUri);
            LOGGER.debug("before getting url: '{}'", searchUri);

            responseEntity = restTemplate.getForEntity(searchUri, SearchTitleResult.class);
            attemptNumber++;

        } while (!isStatusCodeOk(responseEntity));

        return responseEntity.getBody();
    }

    private void checkTimeout(int attemptNumber, URI searchUri) {
        if (attemptNumber > SEARCH_REQUEST_ATTEMPTS_MAX) {
            throw new TimeoutSearchException(searchUri.toString());
        }

        if (attemptNumber > 0) {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(WAIT_TIME_BEFORE_RETRY));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isStatusCodeOk(ResponseEntity responseEntity) {
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }

    URI getSearchUri(SearchParameters params) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(BASIC_OMDBAPI_URL)
                .queryParam(SEARCH_PARAM, params.getSearchText())
                .queryParam(TYPE_PARAM, params.getSearchVideoType().toString().toLowerCase());

        addYearForSearch(params, uriBuilder);

        return uriBuilder.build().toUri();
    }

    private void addYearForSearch(SearchParameters params, UriComponentsBuilder uriBuilder) {
        int yearParam = params.getSearchYear();
        if (yearParam != 0) {
            uriBuilder.queryParam(YEAR_PARAM, yearParam);
        }
    }
}
