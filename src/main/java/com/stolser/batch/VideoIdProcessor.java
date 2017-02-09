package com.stolser.batch;

import com.stolser.entity.Video;
import com.stolser.search.VideoSearcher;
import com.stolser.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class VideoIdProcessor implements ItemProcessor<String, List<Video>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoIdProcessor.class);

    @Autowired
    private VideoSearcher videoSearcher;

    @Autowired
    private VideoService videoService;

    @Override
    public List<Video> process(String imdbId) throws Exception {
        List<Video> result = new ArrayList<>();

        if (!videoExistsInDb(imdbId)) {
            result.addAll(videoSearcher.searchVideos(imdbId));
        }

        return result;
    }

    private boolean videoExistsInDb(String imdbId) {
        return videoService.findByImdbId(imdbId).isPresent();
    }
}
