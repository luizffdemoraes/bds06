package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.projections.MovieReviewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    // SQL raiz na consulta de review por
    @Query(nativeQuery = true, value = "SELECT " +
            "TB_REVIEW.ID AS REVIEW_ID, " +
            "TB_REVIEW.TEXT, " +
            "TB_REVIEW.MOVIE_ID, " +
            "TB_USER.ID AS USER_ID, " +
            "TB_USER.NAME AS USER_NAME, " +
            "TB_USER.EMAIL AS USER_EMAIL " +
            "FROM TB_MOVIE " +
            "INNER JOIN TB_REVIEW ON TB_MOVIE.ID = TB_REVIEW.MOVIE_ID " +
            "INNER JOIN TB_USER ON TB_REVIEW.USER_ID = TB_USER.ID " +
            "WHERE TB_MOVIE.ID = :id")
    List<MovieReviewProjection> findByIdForGetReview(Long id);
}
