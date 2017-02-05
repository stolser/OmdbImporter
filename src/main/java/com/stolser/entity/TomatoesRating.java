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
}
