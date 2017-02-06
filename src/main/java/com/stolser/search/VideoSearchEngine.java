package com.stolser.search;

import com.stolser.entity.Video;

import java.util.List;

public interface VideoSearchEngine {
    List<Video> findVideos(String imdbId);
}
