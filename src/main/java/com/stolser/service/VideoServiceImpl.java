package com.stolser.service;

import com.stolser.entity.Video;
import com.stolser.repository.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<Video> saveVideos(List<Video> videos) {
        try {
            return videoRepository.save(videos);
        } catch (RuntimeException e) {
            LOGGER.error("Something went wrong during saving data into db, but we will continue.");
            return videos;
        }
    }
}
