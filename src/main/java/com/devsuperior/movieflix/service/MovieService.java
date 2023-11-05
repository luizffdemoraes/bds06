package com.devsuperior.movieflix.service;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Transactional
    public MovieDTO findById(Long id) {
        Optional<Movie> optionalMovie = repository.findById(id);
        Movie movie = optionalMovie.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MovieDTO(movie);
    }
}
