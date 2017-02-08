package com.stolser.controller;

import com.stolser.entity.Video;
import com.stolser.search.IdSearcher;
import com.stolser.search.VideoSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class TestController {
    @Autowired
    private VideoSearcher videoSearcher;
    @Autowired
    private IdSearcher idSearcher;

    @RequestMapping(value = "/videos/{id}", method = GET)
    public List<Video> getVideoById(@PathVariable String id) {

        return videoSearcher.searchVideos(id);
    }

    @RequestMapping(value = "/videos", method = POST)
    public List<String> getIdsByTitle(SearchParameters searchParameters) {
        return idSearcher.searchImdbIds(searchParameters);
    }
}
