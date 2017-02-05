package com.stolser.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tomatoes")
@Table(name = "tomatoes")
public class TomatoesRating {
    @Id
    @TableGenerator(name = "tomatoesIdGenerator",
            table = "sequence_storage",
            pkColumnName = "sequence_name",
            pkColumnValue = "tomatoes.id",
            valueColumnName = "sequence_value",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "tomatoesIdGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "tomato_meter")
    private int tomatoMeter;

    @Column(name = "tomato_image")
    private String tomatoImage;

    @Column(name = "tomato_rating")
    private double tomatoRating;

    @Column(name = "tomato_reviews")
    private int tomatoReviews;

    @Column(name = "tomato_fresh")
    private int tomatoFresh;

    @Column(name = "tomato_rotten")
    private int tomatoRotten;

    @Column(name = "tomato_consensus")
    private String tomatoConsensus;

    @Column(name = "tomato_user_meter")
    private int tomatoUserMeter;

    @Column(name = "tomato_user_rating")
    private double tomatoUserRating;

    @Column(name = "tomato_user_reviews")
    private long tomatoUserReviews;

    @Column(name = "tomato_url")
    private String tomatoUrl;

    @Temporal(TemporalType.DATE)
    @Column(name = "dvd_release")
    private Date dvdRelease;

    @Column(name = "box_office")
    private long boxOffice;

    @Column(name = "production")
    private String production;

    @Column(name = "website")
    private String website;

    @OneToOne(mappedBy = "tomatoesRating")
    private Video video;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTomatoMeter() {
        return tomatoMeter;
    }

    public void setTomatoMeter(int tomatoMeter) {
        this.tomatoMeter = tomatoMeter;
    }

    public String getTomatoImage() {
        return tomatoImage;
    }

    public void setTomatoImage(String tomatoImage) {
        this.tomatoImage = tomatoImage;
    }

    public double getTomatoRating() {
        return tomatoRating;
    }

    public void setTomatoRating(double tomatoRating) {
        this.tomatoRating = tomatoRating;
    }

    public int getTomatoReviews() {
        return tomatoReviews;
    }

    public void setTomatoReviews(int tomatoReviews) {
        this.tomatoReviews = tomatoReviews;
    }

    public int getTomatoFresh() {
        return tomatoFresh;
    }

    public void setTomatoFresh(int tomatoFresh) {
        this.tomatoFresh = tomatoFresh;
    }

    public int getTomatoRotten() {
        return tomatoRotten;
    }

    public void setTomatoRotten(int tomatoRotten) {
        this.tomatoRotten = tomatoRotten;
    }

    public String getTomatoConsensus() {
        return tomatoConsensus;
    }

    public void setTomatoConsensus(String tomatoConsensus) {
        this.tomatoConsensus = tomatoConsensus;
    }

    public int getTomatoUserMeter() {
        return tomatoUserMeter;
    }

    public void setTomatoUserMeter(int tomatoUserMeter) {
        this.tomatoUserMeter = tomatoUserMeter;
    }

    public double getTomatoUserRating() {
        return tomatoUserRating;
    }

    public void setTomatoUserRating(double tomatoUserRating) {
        this.tomatoUserRating = tomatoUserRating;
    }

    public long getTomatoUserReviews() {
        return tomatoUserReviews;
    }

    public void setTomatoUserReviews(long tomatoUserReviews) {
        this.tomatoUserReviews = tomatoUserReviews;
    }

    public String getTomatoUrl() {
        return tomatoUrl;
    }

    public void setTomatoUrl(String tomatoUrl) {
        this.tomatoUrl = tomatoUrl;
    }

    public Date getDvdRelease() {
        return dvdRelease;
    }

    public void setDvdRelease(Date dvdRelease) {
        this.dvdRelease = dvdRelease;
    }

    public long getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(long boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
