package com.stolser.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "video", uniqueConstraints = @UniqueConstraint(columnNames = {"imdb_id"}))
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "java_type")
public abstract class Video {
    @Id
    @Column(name = "id")
    @TableGenerator(name = "videoIdGenerator",
            table = "sequence_storage",
            pkColumnName = "sequence_name",
            pkColumnValue = "video.id",
            valueColumnName = "sequence_value",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "videoIdGenerator")
    private Long id;

    @Column(name = "imdb_id")
    private String imdbId;

    @Column(name = "video_type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "year")
    private String year;

    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    private Date releaseDate;

    @Embedded
    private Runtime runtime;

    @ElementCollection
    @CollectionTable(name = "actor")
    @Column(name = "actor_name")
    private List<String> actors;

    @ElementCollection
    @CollectionTable(name = "video_lang")
    @Column(name = "language")
    private List<String> languages;

    @Version
    private int version;


    public enum Type {
        MOVIE, SERIES, EPISODE
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
