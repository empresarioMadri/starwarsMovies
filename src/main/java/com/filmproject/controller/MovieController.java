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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    private ResponseEntity saveMovie(@RequestBody MovieDto movie) {
        try {
            if (movie.getId() != null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Id must be null");
            }
            return ResponseEntity
                    .status(HttpStatus.CREATED).body(movieService.saveMovie(movie));
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
    public ResponseEntity updatePost(@PathVariable("id") Integer id, @RequestBody MovieDto movieDto) {
        if (!Objects.equals(id, movieDto.getId())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("IDs don't match");
        }
        try {
            MovieDto movieDto1 = movieService.getMovieById(id);
            if (movieDto1 != null) {
                movieService.saveMovie(movieDto);
            }
        } catch (ParseException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Release date format not correct");
        } catch (NoSuchElementException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Movie not found");
        }
        return ResponseEntity
                .status(HttpStatus.OK).body(null);
    }
}
