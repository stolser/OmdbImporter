package com.stolser.entity;

import javax.persistence.*;

@Entity(name = "year")
@Table(name = "year")
public class Year {
    @Id
    @TableGenerator(name = "yearIdGenerator",
            table = "sequence_storage",
            pkColumnName = "sequence_name",
            pkColumnValue = "year.id",
            valueColumnName = "sequence_value",
            initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "yearIdGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "year_begin")
    private int begin;

    @Column(name = "year_end")
    private int end;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "is_finished")
    private boolean isFinished;

    @OneToOne(mappedBy = "year")
    private Video video;

    @Version
    private int version;

    public enum Type {
        SINGLE, RANGE
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
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
