package com.filmproject.services.impl;

import com.filmproject.models.Movie;
import com.filmproject.repository.MovieRepository;
import com.filmproject.services.MicroService;
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
