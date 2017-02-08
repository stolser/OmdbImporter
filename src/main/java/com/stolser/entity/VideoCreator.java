package com.stolser.entity;

import com.stolser.search.SingleVideoResult;

public interface VideoCreator {
    Video create(SingleVideoResult jsonVideo);
}
