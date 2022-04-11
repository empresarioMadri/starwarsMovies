package com.filmproject.services.impl;

import com.filmproject.controller.dto.MovieDto;
import com.filmproject.models.Movie;
import com.filmproject.repository.MovieRepository;
import com.filmproject.services.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List getAllMovies() {
        List<Movie> movies = StreamSupport
                .stream(movieRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return movies.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MovieDto getMovieById(int id) {
        Movie movie = movieRepository.findById(id).get();
        return convertToDto(movie);
    }

    @Override
    public MovieDto saveMovie(MovieDto movieDto) throws ParseException {
        Movie movie = convertToEntity(movieDto);
        movie = movieRepository.save(movie);
        return convertToDto(movie);
    }

    private MovieDto convertToDto(Movie movie) {
        MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
        movieDto.setSubmissionDate(movie.getRelease_date());
        return movieDto;
    }

    private Movie convertToEntity(MovieDto movieDto) throws ParseException {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        movie.setRelease_date(movieDto.getSubmissionDateConverted());
        return movie;
    }
}
