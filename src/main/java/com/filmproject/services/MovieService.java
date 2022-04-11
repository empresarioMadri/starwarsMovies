package com.filmproject.services;

import com.filmproject.controller.dto.MovieDto;
import com.filmproject.models.Movie;

import java.text.ParseException;
import java.util.List;

public interface MovieService {

    public List<MovieDto> getAllMovies();

    public MovieDto getMovieById(int id);

    public MovieDto saveMovie(MovieDto movieDto) throws ParseException;
}
