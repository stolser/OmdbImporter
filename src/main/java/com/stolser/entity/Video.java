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
        MOVIE("formPage.movie.label"),
        SERIES("formPage.series.label"),
        EPISODE("formPage.episode.label");

        private String messageKey;

        MediaType(String messageKey) {
            this.messageKey = messageKey;
        }

        public String getMessageKey() {
            return messageKey;
        }
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

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Runtime getRuntime() {
        return runtime;
    }

    public void setRuntime(Runtime runtime) {
        this.runtime = runtime;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        this.writers = writers;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getMetascore() {
        return metascore;
    }

    public void setMetascore(int metascore) {
        this.metascore = metascore;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public long getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(long imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public TomatoesRating getTomatoesRating() {
        return tomatoesRating;
    }

    public void setTomatoesRating(TomatoesRating tomatoesRating) {
        this.tomatoesRating = tomatoesRating;
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

        Video video = (Video) o;

        if (id != null ? !id.equals(video.id) : video.id != null) {
            return false;
        }
        return imdbId != null ? imdbId.equals(video.imdbId) : video.imdbId == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (imdbId != null ? imdbId.hashCode() : 0);
        return result;
    }
}
