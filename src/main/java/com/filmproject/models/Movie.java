package com.filmproject.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Movie {
    @Id
    private Integer id;
    private String title;
    private String opening_crawl;
    private String director;
    private String producer;
    private Date release_date;


}
