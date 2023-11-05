package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<ReviewDTO> insertReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO review = reviewService.insertReview(reviewDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/movies/{id}/reviews").buildAndExpand(review.getId()).toUri();
        return ResponseEntity.created(uri).body(review);
    }
}
