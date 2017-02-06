package com.stolser.entity;

import com.stolser.search.SearchIdResult;

public class MovieCreator extends BasicVideoCreator {
    @Override
    public Video create(SearchIdResult jsonVideo) {
        return fillCommonVideoFields(new Movie(), jsonVideo);
    }
}
