package com.stolser.service;

import com.stolser.entity.Video;

import java.util.List;

public interface VideoService {
    List<Video> saveVideos(List<Video> videos);
}
