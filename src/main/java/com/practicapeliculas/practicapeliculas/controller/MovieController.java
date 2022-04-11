package com.practicapeliculas.practicapeliculas.controller;

import com.practicapeliculas.practicapeliculas.models.Movie;
import com.practicapeliculas.practicapeliculas.services.MicroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MicroService movieService;

    @Operation (summary = "List alla movies")
    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Films found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class)) })
    })
    @GetMapping("/movies")
    private List getAllMovies() {
        return movieService.getAllMovies();
    }

    @Operation (summary = "List movie by ID")
    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Film found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class)) })
    })
    @GetMapping("/movies/{id}")
    private Movie getMovie(@PathVariable("id") int id) {
        return movieService.getMovieById(id);
    }
    @Operation (summary = "Add Movies")
    @ApiResponses (value = {
            @ApiResponse (responseCode = "200", description = "Movie added correctly",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class)) })
    })
    @PostMapping("/movies")
    private int saveMovie(@RequestBody Movie movie) {
        movieService.saveOrUpdate(movie);
        return movie.getId();
    }
}
