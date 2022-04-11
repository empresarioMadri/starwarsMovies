package com.filmproject.services;

import com.filmproject.models.Movie;

import java.util.List;

public interface MicroService {
    public List getAllMovies();

    public Movie getMovieById(int id);

    public void saveOrUpdate(Movie movie);
}
