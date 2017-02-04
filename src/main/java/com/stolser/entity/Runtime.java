package com.stolser.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Runtime {
    @Column(name = "runtime_string")
    private String original;

    @Column(name = "runtime_min")
    private int minutes;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
