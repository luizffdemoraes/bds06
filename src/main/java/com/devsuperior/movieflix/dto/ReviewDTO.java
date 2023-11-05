package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.projections.MovieReviewProjection;

import javax.validation.constraints.NotBlank;

public class ReviewDTO {

    private Long id;

    @NotBlank
    private String text;

    private Long movieId;

    private UserDTO user;

    public ReviewDTO() {
    }

    public ReviewDTO(Long id, String text, Long movieId, Long userId, String userName, String userEmail) {
        this.id = id;
        this.text = text;
        this.movieId = movieId;
        this.user = new UserDTO(userId, userName, userEmail);
    }

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.text = review.getText();
        this.movieId = review.getMovie().getId();
        this.user = new UserDTO(review.getUser());
    }

    public ReviewDTO(MovieReviewProjection projection) {
        this.id = projection.getREVIEW_ID();
        this.text = projection.getTEXT();
        this.movieId = projection.getMOVIE_ID();
        this.user = new UserDTO(projection.getUSER_ID(), projection.getUSER_NAME(), projection.getUSER_EMAIL());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
