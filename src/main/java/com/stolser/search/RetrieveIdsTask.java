package com.stolser.search;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class RetrieveIdsTask extends RecursiveTask<List<String>> {
    private static final int MAX_PAGES_FOR_ONE_THREAD = 5;
    private int firstPage;
    private int lastPage;
    private URI searchUri;

    private RawResultsSearcher rawResultsSearcher = RawResultsSearcherImpl.getInstance();

    RetrieveIdsTask(int firstPage, int lastPage, URI searchUri) {
        this.firstPage = firstPage;
        this.lastPage = lastPage;
        this.searchUri = searchUri;
    }

    @Override
    protected List<String> compute() {
        if (canBeProcessedByOneThread()) {
            return retrieveImdbIds();

        } else {
            int middle = (lastPage - firstPage) / 2;
            RetrieveIdsTask first = new RetrieveIdsTask(firstPage, middle, searchUri);
            first.fork();

            RetrieveIdsTask second = new RetrieveIdsTask(middle + 1, lastPage, searchUri);
            List<String> imdbIds = second.compute();
            imdbIds.addAll(first.join());

            return imdbIds;
        }
    }

    private boolean canBeProcessedByOneThread() {
        return (lastPage - firstPage - 1) <= MAX_PAGES_FOR_ONE_THREAD;
    }

    private List<String> retrieveImdbIds() {
        List<String> imdbIds = new ArrayList<>();
        String uriWithoutPage = searchUri.toString();
        String uriWithPage;

        for (int i = firstPage; i <= lastPage; i++) {
            uriWithPage = uriWithoutPage + "&page=" + i;

            MultiVideoResult result = rawResultsSearcher.searchRawResults(
                    URI.create(uriWithPage), MultiVideoResult.class);
            if (result.isSuccess()) {
                imdbIds.addAll(result.getImdbIds());
            }
        }

        return imdbIds;
    }
}
