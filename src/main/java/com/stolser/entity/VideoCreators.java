package com.stolser.entity;

public final class VideoCreators {
    private VideoCreators() {}

    public static VideoCreator newMovieCreator() {
        return new MovieCreator();
    }

    public static VideoCreator newSeriesCreator() {
        return new SeriesCreator();
    }

    public static VideoCreator newEpisodeCreator() {
        return new EpisodeCreator();
    }

    public static VideoCreator newVideoCreator(VideoType type) {
        switch (type) {
            case MOVIE:
                return newMovieCreator();

            case SERIES:
                return newSeriesCreator();

            case EPISODE:
                return newEpisodeCreator();

            default:
                throw new IllegalArgumentException();
        }
    }
}
