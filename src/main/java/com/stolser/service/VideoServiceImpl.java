package com.stolser.service;

import com.stolser.entity.Video;
import com.stolser.repository.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<Video> saveVideos(List<Video> videos) {
        return videoRepository.save(videos);
    }

    @Override
    public Optional<Video> findByImdbId(String imdbId) {
        return Optional.ofNullable(videoRepository.findByImdbId(imdbId));
    }
}
