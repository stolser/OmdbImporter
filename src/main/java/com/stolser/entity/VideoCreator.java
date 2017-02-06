package com.stolser.entity;

import com.stolser.search.SearchIdResult;

public interface VideoCreator {
    Video create(SearchIdResult jsonVideo);
}
