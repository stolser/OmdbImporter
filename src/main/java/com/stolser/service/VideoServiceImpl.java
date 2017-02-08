package com.stolser.service;

import com.stolser.entity.Video;
import com.stolser.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<Video> saveVideos(List<Video> videos) {
        return videoRepository.save(videos);
    }
}
