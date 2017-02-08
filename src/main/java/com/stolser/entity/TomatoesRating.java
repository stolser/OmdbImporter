package com.stolser.entity;

import javax.persistence.*;

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
    private String tomatoMeter;

    @Column(name = "tomato_image")
    private String tomatoImage;

    @Column(name = "tomato_rating")
    private String tomatoRating;

    @Column(name = "tomato_reviews")
    private String tomatoReviews;

    @Column(name = "tomato_fresh")
    private String tomatoFresh;

    @Column(name = "tomato_rotten")
    private String tomatoRotten;

    @Column(name = "tomato_consensus")
    private String tomatoConsensus;

    @Column(name = "tomato_user_meter")
    private String tomatoUserMeter;

    @Column(name = "tomato_user_rating")
    private String tomatoUserRating;

    @Column(name = "tomato_user_reviews")
    private String tomatoUserReviews;

    @Column(name = "tomato_url")
    private String tomatoUrl;

    @Column(name = "dvd_release")
    private String dvdRelease;

    @Column(name = "box_office")
    private String boxOffice;

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

    public String getTomatoMeter() {
        return tomatoMeter;
    }

    public void setTomatoMeter(String tomatoMeter) {
        this.tomatoMeter = tomatoMeter;
    }

    public String getTomatoImage() {
        return tomatoImage;
    }

    public void setTomatoImage(String tomatoImage) {
        this.tomatoImage = tomatoImage;
    }

    public String getTomatoRating() {
        return tomatoRating;
    }

    public void setTomatoRating(String tomatoRating) {
        this.tomatoRating = tomatoRating;
    }

    public String getTomatoReviews() {
        return tomatoReviews;
    }

    public void setTomatoReviews(String tomatoReviews) {
        this.tomatoReviews = tomatoReviews;
    }

    public String getTomatoFresh() {
        return tomatoFresh;
    }

    public void setTomatoFresh(String tomatoFresh) {
        this.tomatoFresh = tomatoFresh;
    }

    public String getTomatoRotten() {
        return tomatoRotten;
    }

    public void setTomatoRotten(String tomatoRotten) {
        this.tomatoRotten = tomatoRotten;
    }

    public String getTomatoConsensus() {
        return tomatoConsensus;
    }

    public void setTomatoConsensus(String tomatoConsensus) {
        this.tomatoConsensus = tomatoConsensus;
    }

    public String getTomatoUserMeter() {
        return tomatoUserMeter;
    }

    public void setTomatoUserMeter(String tomatoUserMeter) {
        this.tomatoUserMeter = tomatoUserMeter;
    }

    public String getTomatoUserRating() {
        return tomatoUserRating;
    }

    public void setTomatoUserRating(String tomatoUserRating) {
        this.tomatoUserRating = tomatoUserRating;
    }

    public String getTomatoUserReviews() {
        return tomatoUserReviews;
    }

    public void setTomatoUserReviews(String tomatoUserReviews) {
        this.tomatoUserReviews = tomatoUserReviews;
    }

    public String getTomatoUrl() {
        return tomatoUrl;
    }

    public void setTomatoUrl(String tomatoUrl) {
        this.tomatoUrl = tomatoUrl;
    }

    public String getDvdRelease() {
        return dvdRelease;
    }

    public void setDvdRelease(String dvdRelease) {
        this.dvdRelease = dvdRelease;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TomatoesRating that = (TomatoesRating) o;

        if (tomatoMeter != null ? !tomatoMeter.equals(that.tomatoMeter) : that.tomatoMeter != null) {
            return false;
        }
        if (tomatoImage != null ? !tomatoImage.equals(that.tomatoImage) : that.tomatoImage != null) {
            return false;
        }
        if (tomatoRating != null ? !tomatoRating.equals(that.tomatoRating) : that.tomatoRating != null) {
            return false;
        }
        if (tomatoReviews != null ? !tomatoReviews.equals(that.tomatoReviews) : that.tomatoReviews != null) {
            return false;
        }
        if (tomatoFresh != null ? !tomatoFresh.equals(that.tomatoFresh) : that.tomatoFresh != null) {
            return false;
        }
        if (tomatoRotten != null ? !tomatoRotten.equals(that.tomatoRotten) : that.tomatoRotten != null) {
            return false;
        }
        if (tomatoConsensus != null ? !tomatoConsensus.equals(that.tomatoConsensus) : that.tomatoConsensus != null) {
            return false;
        }
        if (tomatoUserMeter != null ? !tomatoUserMeter.equals(that.tomatoUserMeter) : that.tomatoUserMeter != null) {
            return false;
        }
        if (tomatoUserRating != null ? !tomatoUserRating.equals(that.tomatoUserRating) : that.tomatoUserRating != null) {
            return false;
        }
        if (tomatoUserReviews != null ? !tomatoUserReviews.equals(that.tomatoUserReviews) : that.tomatoUserReviews != null) {
            return false;
        }
        if (tomatoUrl != null ? !tomatoUrl.equals(that.tomatoUrl) : that.tomatoUrl != null) {
            return false;
        }
        if (dvdRelease != null ? !dvdRelease.equals(that.dvdRelease) : that.dvdRelease != null) {
            return false;
        }
        if (boxOffice != null ? !boxOffice.equals(that.boxOffice) : that.boxOffice != null) {
            return false;
        }
        if (production != null ? !production.equals(that.production) : that.production != null) {
            return false;
        }
        return website != null ? website.equals(that.website) : that.website == null;

    }

    @Override
    public int hashCode() {
        int result = tomatoMeter != null ? tomatoMeter.hashCode() : 0;
        result = 31 * result + (tomatoImage != null ? tomatoImage.hashCode() : 0);
        result = 31 * result + (tomatoRating != null ? tomatoRating.hashCode() : 0);
        result = 31 * result + (tomatoReviews != null ? tomatoReviews.hashCode() : 0);
        result = 31 * result + (tomatoFresh != null ? tomatoFresh.hashCode() : 0);
        result = 31 * result + (tomatoRotten != null ? tomatoRotten.hashCode() : 0);
        result = 31 * result + (tomatoConsensus != null ? tomatoConsensus.hashCode() : 0);
        result = 31 * result + (tomatoUserMeter != null ? tomatoUserMeter.hashCode() : 0);
        result = 31 * result + (tomatoUserRating != null ? tomatoUserRating.hashCode() : 0);
        result = 31 * result + (tomatoUserReviews != null ? tomatoUserReviews.hashCode() : 0);
        result = 31 * result + (tomatoUrl != null ? tomatoUrl.hashCode() : 0);
        result = 31 * result + (dvdRelease != null ? dvdRelease.hashCode() : 0);
        result = 31 * result + (boxOffice != null ? boxOffice.hashCode() : 0);
        result = 31 * result + (production != null ? production.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        return result;
    }
}
