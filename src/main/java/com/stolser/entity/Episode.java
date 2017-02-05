package com.stolser.entity;

import javax.persistence.*;

@Entity(name = "episode")
@DiscriminatorValue(value = "episode")
public class Episode extends Video {
    @Column(name = "episode_number")
    private int number;

    @Column(name = "season_number")
    private int seasonNumber;

    @ManyToOne
    @JoinColumn(name = "series")
    private Series series;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
