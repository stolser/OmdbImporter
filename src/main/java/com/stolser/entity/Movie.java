package com.stolser.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "movie")
public class Movie extends Video {
}
