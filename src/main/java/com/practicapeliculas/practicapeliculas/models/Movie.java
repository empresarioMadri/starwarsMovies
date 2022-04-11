package com.practicapeliculas.practicapeliculas.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Movie {
    @Id
    private int id;
    private String title;
    private String opening_crawl;
    private String director;
    private String producer;
    private String release_date;


}
