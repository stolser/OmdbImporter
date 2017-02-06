package com.stolser.controller;

import com.stolser.entity.Video;
import com.stolser.repository.VideoRepository;
import com.stolser.search.IdSearchEngine;
import com.stolser.search.VideoSearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private VideoSearchEngine videoSearchEngine;
    @Autowired
    private IdSearchEngine idSearchEngine;

    @Autowired
    private VideoRepository videoRepository;

    @RequestMapping(value = "/videos/{id}", method = RequestMethod.GET)
    public List<Video> getVideoById(@PathVariable String id) {
        System.out.println("TestController: id = " + id);
        List<Video> videos = videoSearchEngine.findVideos(id);
        videoRepository.save(videos);

        return videos;
    }

    @RequestMapping(value = "/videos", method = RequestMethod.POST)
    public List<String> getIdsByTitle(SearchParameters searchParameters) {
        System.out.println("searchParameters = " + searchParameters);

        return idSearchEngine.findVideoIds(searchParameters);
    }
}
