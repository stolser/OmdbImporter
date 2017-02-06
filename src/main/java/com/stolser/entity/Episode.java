package com.stolser.entity;

import javax.persistence.*;

@Entity(name = "episode")
@DiscriminatorValue(value = "episode")
public class Episode extends Video {
    @Column(name = "episode_number")
    private int episodeNumber;

    @Column(name = "season_number")
    private int seasonNumber;

    @ManyToOne
    @JoinColumn(name = "series")
    private Series series;

    protected Episode() {}

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }


}
