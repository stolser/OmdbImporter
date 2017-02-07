package com.stolser.search;

import com.stolser.entity.Episode;
import com.stolser.entity.Series;
import com.stolser.entity.Video;
import com.stolser.entity.VideoCreators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

//@Component
public class ConcurrentVideoSearcher implements VideoSearcher {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentVideoSearcher.class);
    private static final String GETTING_RESULTS_FROM_FUTURE_EXCEPTION_MESSAGE =
            "Getting results from future has thrown an exception.";

    @Autowired
    RawResultsSearcher rawResultsSearcher;

    @Autowired
    ExecutorService executor;

    @Override
    public List<Video> searchVideos(String imdbId) {
        List<Video> videos = new ArrayList<>();

        searchSingleVideo(imdbId).ifPresent(video -> {
            videos.add(video);

            if (video instanceof Series) {
                videos.addAll(searchAllEpisodes((Series) video));
            }
        });

        return videos;
    }

    Optional<Video> searchSingleVideo(String imdbId) {
        SingleVideoResult result = fetchSingleVideoResult(imdbId);

        if (result.isSuccess()) {
            Video existedVideo = VideoCreators.newVideoCreator(parseVideoType(result)).create(result);

            return Optional.of(existedVideo);
        }

        return Optional.empty();
    }

    private List<Episode> searchAllEpisodes(Series series) {
        List<Future<List<Episode>>> futureEpisodesList = new ArrayList<>();

        for (int number = 1; number <= series.getTotalSeasons(); number++) {
            URI seasonSearchUri = SearchUriProvider
                    .getUriToSearchEpisodesBySeasonNumber(series, number);

            futureEpisodesList.add(executor.submit(new EpisodesSearcher(seasonSearchUri)));
        }

        return sumUpEpisodesFromAllSeasons(futureEpisodesList);
    }

    private SingleVideoResult fetchSingleVideoResult(String imdbId) {
        URI searchUri = SearchUriProvider.getUriToSearchByImdbId(imdbId);

        return rawResultsSearcher.searchRawResults(searchUri, SingleVideoResult.class);
    }

    private Video.Type parseVideoType(SingleVideoResult firstResult) {
        return Video.Type.valueOf(firstResult.getType().toUpperCase());
    }

    private List<Episode> sumUpEpisodesFromAllSeasons(List<Future<List<Episode>>> futureEpisodesList) {
        List<Episode> episodes = new ArrayList<>();

        futureEpisodesList.forEach(future -> episodes.addAll(getEpisodesFromFuture(future)));

        return episodes;
    }

    private List<Episode> getEpisodesFromFuture(Future<List<Episode>> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new GettingFutureResultsException(GETTING_RESULTS_FROM_FUTURE_EXCEPTION_MESSAGE, e);
        }
    }

    private class EpisodesSearcher implements Callable<List<Episode>> {
        private URI seasonSearchUri;

        EpisodesSearcher(URI seasonSearchUri) {
            this.seasonSearchUri = seasonSearchUri;
        }

        @Override
        public List<Episode> call() throws Exception {
            List<Episode> episodes = new ArrayList<>();
            MultiVideoResult episodesResult = rawResultsSearcher.searchRawResults(
                    seasonSearchUri, MultiVideoResult.class);

            if (episodesResult.isSuccess()) {
                for (String imdbId : episodesResult.getImdbIds()) {
                    searchSingleVideo(imdbId).ifPresent(video -> {
                        if (video instanceof Episode) {
                            episodes.add((Episode) video);
                        }
                    });
                }
            }

            return episodes;
        }
    }
}
