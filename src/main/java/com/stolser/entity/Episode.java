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
}
