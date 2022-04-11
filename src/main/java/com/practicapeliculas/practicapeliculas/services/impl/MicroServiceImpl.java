package com.practicapeliculas.practicapeliculas.services.impl;

import com.practicapeliculas.practicapeliculas.models.Movie;
import com.practicapeliculas.practicapeliculas.repository.MovieRepository;
import com.practicapeliculas.practicapeliculas.services.MicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MicroServiceImpl implements MicroService {

    @Autowired
    MovieRepository movieRepository;

    public List getAllMovies() {
        List movies = new ArrayList();
        movieRepository.findAll().forEach(movie -> movies.add(movie));
        return movies;
    }

    public Movie getMovieById(int id) {
        return movieRepository.findById(id).get();
    }

    public void saveOrUpdate(Movie movie) {
        movieRepository.save(movie);
    }
}
