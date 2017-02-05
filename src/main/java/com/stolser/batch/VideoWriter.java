package com.stolser.batch;

import com.stolser.entity.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class VideoWriter implements ItemWriter<List<Video>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoWriter.class);

    @Override
    public void write(List<? extends List<Video>> list) throws Exception {
        LOGGER.debug("persisting videos into db...");
    }
}
