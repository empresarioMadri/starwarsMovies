package com.practicapeliculas.practicapeliculas.repository;

import com.practicapeliculas.practicapeliculas.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
}
