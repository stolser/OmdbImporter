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

public class SearchUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchUtils.class);
    private static final String BASIC_OMDBAPI_URL = "http://www.omdbapi.com/";
    private static final String SEARCH_PARAM = "s";
    private static final String TYPE_PARAM = "type";
    private static final String YEAR_PARAM = "y";
    private static final int SEARCH_REQUEST_ATTEMPTS_MAX = 2;
    private static final int WAIT_TIME_BEFORE_RETRY = 2;
    private static final String ID_PARAM = "i";
    private static final String PLOT_PARAM = "plot";
    private static final String TOMATOES_PARAM = "tomatoes";
    private static final String FULL_VALUE = "full";
    private static final String TRUE_VALUE = "true";

    @Autowired
    private RestTemplate restTemplate;

    <T> T getSearchResult(URI searchUri, Class<T> tClass) {
        ResponseEntity<T> responseEntity;
        int attemptNumber = 0;

        do {
            checkTimeout(attemptNumber, searchUri);
            LOGGER.debug("before getting url: '{}'", searchUri);

            responseEntity = restTemplate.getForEntity(searchUri, tClass);
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
                String message = String.format("Sleeping during getting a uri (%s) was interrupted.", searchUri);
                LOGGER.error(message, e);
            }
        }
    }

    private boolean isStatusCodeOk(ResponseEntity responseEntity) {
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }

    URI getSearchWithParamsUri(SearchParameters params) {
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

    URI getSearchWithIdUri(String id) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(BASIC_OMDBAPI_URL)
                .queryParam(ID_PARAM, id)
                .queryParam(PLOT_PARAM, FULL_VALUE)
                .queryParam(TOMATOES_PARAM, TRUE_VALUE);

        return uriBuilder.build().toUri();
    }
}
