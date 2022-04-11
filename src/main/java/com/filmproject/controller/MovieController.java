package com.filmproject.controller;

import com.filmproject.controller.dto.MovieDto;
import com.filmproject.models.Movie;
import com.filmproject.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Operation(summary = "List all movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Films found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDto.class))})
    })
    @GetMapping("/movies")
    private List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @Operation(summary = "List movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Film found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDto.class))})
    })
    @GetMapping("/movies/{id}")
    private MovieDto getMovie(@PathVariable("id") int id) {
        return movieService.getMovieById(id);
    }

    @Operation(summary = "Add Movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie added correctly",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDto.class))})
    })
    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private MovieDto saveMovie(@RequestBody MovieDto movie) {
        try {
            return movieService.saveMovie(movie);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Release date format not correct");
        }
    }

    @Operation(summary = "Update Movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated correctly",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDto.class))})
    })

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@PathVariable("id") Long id, @RequestBody MovieDto movieDto) {
        if(!Objects.equals(id, movieDto.getId())){
            throw new IllegalArgumentException("IDs don't match");
        }
        try {
            movieService.saveMovie(movieDto);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Release date format not correct");
        }
    }
}
