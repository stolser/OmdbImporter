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
    private String begin;

    @Column(name = "year_end")
    private String end;

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

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Year year = (Year) o;

        if (isFinished != year.isFinished) {
            return false;
        }
        if (begin != null ? !begin.equals(year.begin) : year.begin != null) {
            return false;
        }
        if (end != null ? !end.equals(year.end) : year.end != null) {
            return false;
        }
        if (type != year.type) {
            return false;
        }
        return video != null ? video.equals(year.video) : year.video == null;

    }

    @Override
    public int hashCode() {
        int result = begin != null ? begin.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (isFinished ? 1 : 0);
        result = 31 * result + (video != null ? video.hashCode() : 0);
        return result;
    }
}
