package com.practicapeliculas.practicapeliculas.services;

import com.practicapeliculas.practicapeliculas.models.Movie;

import java.util.List;

public interface MicroService {
    public List getAllMovies();

    public Movie getMovieById(int id);

    public void saveOrUpdate(Movie movie);
}
