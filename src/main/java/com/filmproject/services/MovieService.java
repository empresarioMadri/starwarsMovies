package com.filmproject.services;

import com.filmproject.controller.dto.MovieDto;
import com.filmproject.models.Movie;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

public interface MovieService {

    public List<MovieDto> getAllMovies();

    public MovieDto getMovieById(int id) throws NoSuchElementException;

    public MovieDto saveMovie(MovieDto movieDto) throws ParseException;
}
