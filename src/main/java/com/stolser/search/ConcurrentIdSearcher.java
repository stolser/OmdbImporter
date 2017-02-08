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
    private static final int NUMBER_OF_THREADS = 10;
    private static final int MAX_WAITING_TIME = 20;

    @Autowired
    RawResultsSearcher rawResultsSearcher;

    @Override
    public List<String> searchImdbIds(SearchParameters params) {
        URI searchUri = SearchUriProvider.getUriToSearchByParams(params);
        System.out.println("rawResultsSearcher = " + rawResultsSearcher);
        MultiVideoResult firstResult = rawResultsSearcher.searchRawResults(searchUri, MultiVideoResult.class);

        return firstResult.isSuccess() ? searchAllImdbIds(searchUri, firstResult) : new ArrayList<>();
    }

    private List<String> searchAllImdbIds(URI searchUri, MultiVideoResult firstResult) {
        ForkJoinPool pool = new ForkJoinPool(NUMBER_OF_THREADS);
        RecursiveTask<List<String>> task = new RetrieveIdsTask(1, getTotalPages(firstResult), searchUri);
        pool.invoke(task);

        long t0 = System.currentTimeMillis();

        while(true) {
            sleepForSomeTime();

            if (task.isDone()) {
                return shutdownPoolAndGetResult(pool, task);

            } else if (processingTakesTooLong(t0)) {
                return shutdownPoolAndThrow(pool);
            }
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

    private List<String> shutdownPoolAndThrow(ForkJoinPool pool) {
        pool.shutdown();
        throw new RuntimeException("Retrieving all imdbIds took too long.");
    }

    private boolean processingTakesTooLong(long t0) {
        return System.currentTimeMillis() - t0 > TimeUnit.SECONDS.toMillis(MAX_WAITING_TIME);
    }

    private void sleepForSomeTime() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int getTotalPages(MultiVideoResult result) {
        return (result.getTotalResults() / RESULT_ITEMS_ON_PAGE) + 1;
    }
}
