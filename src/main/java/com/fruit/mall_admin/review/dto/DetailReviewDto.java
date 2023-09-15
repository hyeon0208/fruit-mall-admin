package com.fruit.mall_admin.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class DetailReviewDto {
    private Long reviewId;
    private String userName;
    private String reviewContents;
    private Timestamp reviewCreatedAt;

    @Builder
    public DetailReviewDto(Long reviewId, String userName, String reviewContents, Timestamp reviewCreatedAt) {
        this.reviewId = reviewId;
        this.userName = userName;
        this.reviewContents = reviewContents;
        this.reviewCreatedAt = reviewCreatedAt;
    }
}
