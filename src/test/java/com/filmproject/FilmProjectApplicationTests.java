package com.filmproject;

import com.filmproject.controller.dto.MovieDto;
import com.filmproject.models.Movie;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FilmProjectApplicationTests {

	private ModelMapper modelMapper = new ModelMapper();

	private static final SimpleDateFormat dateFormat
			= new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Test
	public void whenConvertMovieEntityToMovieDto_thenCorrect() throws ParseException {
		Movie movie = new Movie();
		movie.setId(1);
		movie.setTitle(randomAlphabetic(6));
		movie.setRelease_date(new Date());

		MovieDto movieDto = modelMapper.map(movie, MovieDto.class);
		movieDto.setSubmissionDate(movie.getRelease_date());
		assertEquals(movie.getId(), movieDto.getId());
		assertEquals(movie.getTitle(), movieDto.getTitle());
		assertEquals(dateFormat.format(movie.getRelease_date()),movieDto.getRelease_date());
	}

	@Test
	public void whenConvertMovieDtoToMovieEntity_thenCorrect() throws ParseException {
		MovieDto movieDto = new MovieDto();
		movieDto.setId(1);
		movieDto.setTitle(randomAlphabetic(6));
		movieDto.setRelease_date(dateFormat.format(new Date()));

		Movie movie = modelMapper.map(movieDto, Movie.class);
		movie.setRelease_date(movieDto.getSubmissionDateConverted());
		assertEquals(movieDto.getId(), movie.getId());
		assertEquals(movieDto.getTitle(), movie.getTitle());
		assertEquals(movieDto.getRelease_date(),dateFormat.format(movie.getRelease_date()));
	}

}
