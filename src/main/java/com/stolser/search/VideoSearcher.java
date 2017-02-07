package com.stolser.search;

import com.stolser.entity.Video;

import java.util.List;

public interface VideoSearcher {
    /**
     * Fetches the data from the OMDB API and converts it into a list of {@link Video} objects.
     * @return A list of videos. If the video is a movie, the list contains only one item (this movie).
     * If the video is a series, the list contains one video of the type {@link com.stolser.entity.Series}
     * and also can contain multiple items of the type {@link com.stolser.entity.Episode}.
     */
    List<Video> searchVideos(String imdbId);
}
