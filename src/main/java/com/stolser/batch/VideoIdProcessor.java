package com.stolser.batch;

import com.stolser.entity.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

public class VideoIdProcessor implements ItemProcessor<String, List<Video>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoIdProcessor.class);

    @Override
    public List<Video> process(String s) throws Exception {
        LOGGER.debug("fetching data by imdbId...");

        return new ArrayList<Video>();
    }
}
