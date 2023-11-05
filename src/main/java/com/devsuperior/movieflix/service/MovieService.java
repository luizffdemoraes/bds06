package com.devsuperior.movieflix.service;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
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

    @Autowired
    private ReviewRepository reviewRepository;

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
}
