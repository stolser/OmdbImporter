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
}
