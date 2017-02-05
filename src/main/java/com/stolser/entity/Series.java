package com.stolser.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "series")
@DiscriminatorValue(value = "series")
public class Series extends Video {
    @Column(name = "total_seasons")
    private int totalSeasons;

    @OneToMany(mappedBy="series", cascade={CascadeType.ALL})
    private List<Episode> episodes;

    public int getTotalSeasons() {
        return totalSeasons;
    }

    public void setTotalSeasons(int totalSeasons) {
        this.totalSeasons = totalSeasons;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}
