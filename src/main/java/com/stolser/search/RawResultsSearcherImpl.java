package com.stolser.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class RawResultsSearcherImpl implements RawResultsSearcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(RawResultsSearcherImpl.class);
    private static final int SEARCH_REQUEST_ATTEMPTS_MAX = 2;
    private static final int WAIT_TIME_BEFORE_RETRY = 2;
    private static final String SLEEPING_DURING_GETTING_URI_WAS_INTERRUPTED =
            "Sleeping during getting a uri (%s) was interrupted.";
    private static final String BEFORE_GETTING_URL = "before fetching data from url: '{}'";

    private RawResultsSearcherImpl() {}

    private static class InstanceHolder {
        final static RawResultsSearcherImpl INSTANCE = new RawResultsSearcherImpl();
    }

    public static RawResultsSearcherImpl getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T> T searchRawResults(URI searchUri, Class<T> resultsClass) {
        ResponseEntity<T> responseEntity;
        int attemptNumber = 0;

        do {
            checkTimeout(attemptNumber, searchUri);
            LOGGER.debug(BEFORE_GETTING_URL, searchUri);

            responseEntity = restTemplate.getForEntity(searchUri, resultsClass);
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
                String message = String.format(SLEEPING_DURING_GETTING_URI_WAS_INTERRUPTED, searchUri);
                LOGGER.error(message, e);
            }
        }
    }

    private boolean isStatusCodeOk(ResponseEntity responseEntity) {
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }
}
