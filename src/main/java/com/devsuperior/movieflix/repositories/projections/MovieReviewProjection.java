package com.devsuperior.movieflix.repositories.projections;

public interface MovieReviewProjection {
    Long getREVIEW_ID();
    String getTEXT();
    Long getMOVIE_ID();
    Long getUSER_ID();
    String getUSER_NAME();
    String getUSER_EMAIL();
}
