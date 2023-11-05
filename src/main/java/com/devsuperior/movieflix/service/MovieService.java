package com.devsuperior.movieflix.service;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Transactional
    public MovieDTO findById(Long id) {
        Optional<Movie> optionalMovie = repository.findById(id);
        Movie movie = optionalMovie.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MovieDTO(movie);
    }

    @Transactional
    public List<ReviewDTO> findByIdForGetReview(Long id) {
        /*
            Solução SQL Raiz
            List<MovieReviewProjection> movieReviewProjections = reviewRepository.findReviewsByMovieId2(id);
            List<ReviewDTO> reviewDTOList = movieReviewProjections.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
         */

        // Solução JPQL
        List<ReviewDTO> reviewDTOS = reviewRepository.findReviewsByMovieIdJPQL(id);
        return reviewDTOS;
    }


    @Transactional
    public Page<MovieDTO> findByGenreIdForGenPage(Long genreId, Pageable pageable) {
        Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
        Page<Movie> movies = repository.findMovieByGenre(genre, pageable);
        return movies.map(x -> new MovieDTO(x));
    }
}
