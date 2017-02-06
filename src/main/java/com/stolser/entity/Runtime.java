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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Runtime runtime = (Runtime) o;

        if (minutes != runtime.minutes) {
            return false;
        }
        return original != null ? original.equals(runtime.original) : runtime.original == null;

    }

    @Override
    public int hashCode() {
        int result = original != null ? original.hashCode() : 0;
        result = 31 * result + minutes;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Runtime{original='%s', minutes=%d}", original, minutes);
    }
}
