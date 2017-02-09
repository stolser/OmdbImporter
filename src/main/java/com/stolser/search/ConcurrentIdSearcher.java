package com.stolser.search;

import com.stolser.controller.SearchParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class ConcurrentIdSearcher implements IdSearcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentIdSearcher.class);
    private static final int RESULT_ITEMS_ON_PAGE = 10;
    private static final int MAX_WAITING_TIME = 20;
    private static final int FIRST_PAGE_INDEX = 1;
    private static final int SLEEP_TIME_BEFORE_NEW_REQUEST = 500;
    private static final int NUMBER_OF_THREADS = 10;

    @Autowired
    private RawResultsSearcher rawResultsSearcher;

    @Override
    public List<String> searchImdbIds(SearchParameters params) {
        URI searchUri = SearchUriProvider.getUriToSearchByParams(params);
        MultiVideoResult firstResult = rawResultsSearcher.searchRawResults(searchUri, MultiVideoResult.class);

        return firstResult.isSuccess() ? searchAllImdbIds(searchUri, firstResult) : new ArrayList<>();
    }

    private List<String> searchAllImdbIds(URI searchUri, MultiVideoResult firstResult) {
        RecursiveTask<List<String>> task =
                new RetrieveIdsTask(FIRST_PAGE_INDEX, getTotalPages(firstResult), searchUri);
        ForkJoinPool pool = new ForkJoinPool(NUMBER_OF_THREADS);

        pool.invoke(task);

        return getResultAndShutdownPool(task, pool);
    }

    private List<String> getResultAndShutdownPool(RecursiveTask<List<String>> task, ForkJoinPool pool) {
        long t0 = System.currentTimeMillis();

        while (true) {
            sleepForSomeTime();

            if (task.isDone()) {
                return shutdownPoolAndGetResult(pool, task);

            } else if (processingTakesTooLong(t0)) {
                return shutdownPoolAndThrow(pool);
            }
        }
    }

    private int getTotalPages(MultiVideoResult result) {
        return getNumberOfFullPages(result) + getExtraOneIfNeeded(result);
    }

    private void sleepForSomeTime() {
        try {
            Thread.sleep(SLEEP_TIME_BEFORE_NEW_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<String> shutdownPoolAndGetResult(ForkJoinPool pool, RecursiveTask<List<String>> task) {
        try {
            pool.shutdown();
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Exception during getting result from task.");
            throw new RuntimeException(e);
        }
    }

    private boolean processingTakesTooLong(long t0) {
        return System.currentTimeMillis() - t0 > TimeUnit.SECONDS.toMillis(MAX_WAITING_TIME);
    }

    private List<String> shutdownPoolAndThrow(ForkJoinPool pool) {
        pool.shutdown();
        throw new RuntimeException("Retrieving all imdbIds took too long.");
    }

    private int getNumberOfFullPages(MultiVideoResult result) {
        return result.getTotalResults() / RESULT_ITEMS_ON_PAGE;
    }

    private int getExtraOneIfNeeded(MultiVideoResult result) {
        return (result.getTotalResults() % RESULT_ITEMS_ON_PAGE > 0) ? 1 : 0;
    }
}
