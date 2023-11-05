package com.devsuperior.movieflix.controllers;


import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO movie = movieService.findById(id);
        return ResponseEntity.ok().body(movie);
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findByIdForGetReview(@PathVariable Long id) {
        List<ReviewDTO> reviewDTOList = movieService.findByIdForGetReview(id);
        return ResponseEntity.ok().body(reviewDTOList);
    }

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> findByGenreIdForGenPage(
            @RequestParam(value = "genreId", defaultValue = "0") Long genreId,
            Pageable pageable) {
        Page<MovieDTO> reviewDTOList = movieService.findByGenreIdForGenPage(genreId, pageable);
        return ResponseEntity.ok().body(reviewDTOList);
    }
}
