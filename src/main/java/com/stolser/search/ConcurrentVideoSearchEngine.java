package com.stolser.search;

import com.stolser.entity.Video;
import com.stolser.entity.VideoCreators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ConcurrentVideoSearchEngine implements VideoSearchEngine {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentVideoSearchEngine.class);

    @Autowired
    SearchUtils searchUtils;

    @Override
    public List<Video> findVideos(String imdbId) {
        List<Video> videos = new ArrayList<>();
        SearchIdResult firstResult = getFirstResult(imdbId);

        LOGGER.debug("firstResult = " + firstResult);

        if (firstResult.isSuccess()) {
            videos.add(VideoCreators.newVideoCreator(parseVideoType(firstResult)).create(firstResult));
        }

        return videos;
    }

    private Video.Type parseVideoType(SearchIdResult firstResult) {
        return Video.Type.valueOf(firstResult.getType().toUpperCase());
    }

    private SearchIdResult getFirstResult(String imdbId) {
        URI searchUri = searchUtils.getSearchWithIdUri(imdbId);

        return searchUtils.getSearchResult(searchUri, SearchIdResult.class);
    }
}
