package com.stolser.entity;

import com.stolser.search.SingleVideoResult;

public class MovieCreator extends BasicVideoCreator {
    @Override
    public Video create(SingleVideoResult jsonVideo) {
        return fillCommonVideoFields(new Movie(), jsonVideo);
    }
}
