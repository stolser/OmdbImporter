package com.stolser.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "video")
@Table(name = "video", uniqueConstraints = @UniqueConstraint(columnNames = {"imdb_id"}))
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "java_type")
public abstract class Video {
    @Id
    @TableGenerator(name = "videoIdGenerator",
            table = "sequence_storage",
            pkColumnName = "sequence_name",
            pkColumnValue = "video.id",
            valueColumnName = "sequence_value",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "videoIdGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "imdb_id")
    private String imdbId;

    @Enumerated(EnumType.STRING)
    @Column(name = "video_type")
    private MediaType mediaType;

    @Column(name = "title")
    private String title;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "years")
    private Year year;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "mpaa_rating")
    private MpaaRating mpaaRating;

    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    private Date releaseDate;

    @Embedded
    private Runtime runtime;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "genre")
    @Column(name = "genre_name")
    private List<Genre> genres;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "director")
    @Column(name = "director_name")
    private List<String> directors;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "writer")
    @Column(name = "writer_name")
    private List<String> writers;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "actor")
    @Column(name = "actor_name")
    private List<String> actors;

    @Column(name = "plot")
    private String plot;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "video_lang")
    @Column(name = "language")
    private List<String> languages;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "video_country")
    @Column(name = "country")
    private List<String> countries;

    @Column(name = "awards")
    private String awards;

    @Column(name = "poster")
    private String poster;

    @Column(name = "metascore")
    private int metascore;

    @Column(name = "imdb_rating")
    private double imdbRating;

    @Column(name = "imdb_votes")
    private long imdbVotes;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "tomatoes_rating")
    private TomatoesRating tomatoesRating;

    @Version
    private int version;

    public enum MediaType {
        MOVIE, SERIES, EPISODE
    }

    public enum MpaaRating {
        G("g.shortDescription", "g.longDescription"),
        PG("pg.shortDescription", "pg.longDescription"),
        PG_13("pg_13.shortDescription", "pg_13.longDescription"),
        TV_14("tv_14.shortDescription", "tv_14.longDescription"),
        R("r.shortDescription", "r.longDescription"),
        X("x.shortDescription", "x.longDescription"),
        NC_17("nc_17.shortDescription", "nc_17.longDescription"),
        NOT_RATED("not_rated.shortDescription", "not_rated.longDescription");

        private String shortDescription;
        private String longDescription;

        MpaaRating(String shortDescription, String longDescription) {
            this.shortDescription = shortDescription;
            this.longDescription = longDescription;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public String getLongDescription() {
            return longDescription;
        }
    }

    public enum Genre {
        COMEDY, SHORT, DRAMA, FAMILY, TALK_SHOW, ROMANCE, ANIMATION,
        MUSIC, ACTION, FANTASY, ADVENTURE, SCI_FI, CRIME, GAME_SHOW,
        NEWS, HORROR, MYSTERY, MUSICAL, DOCUMENTARY, THRILLER, REALITY_TV,
        SPORT, HISTORY, WESTERN, WAR, BIOGRAPHY, ADULT;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
