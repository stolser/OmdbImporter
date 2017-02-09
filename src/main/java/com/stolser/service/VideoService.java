package com.stolser.service;

import com.stolser.entity.Video;

import java.util.List;
import java.util.Optional;

public interface VideoService {
    List<Video> saveVideos(List<Video> videos);
    Optional<Video> findByImdbId(String imdbId);
}
