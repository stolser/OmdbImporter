package com.stolser.batch;

import com.stolser.entity.Video;
import com.stolser.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class VideoWriter implements ItemWriter<List<Video>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoWriter.class);

    @Autowired
    private VideoService videoService;

    @Override
    public void write(List<? extends List<Video>> list) throws Exception {

        list.forEach(videoService::saveVideos);
    }
}
