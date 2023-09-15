package com.fruit.mall_admin.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class ReviewResDto {
    private int num;
    private Long productId;
    private Long reviewId;
    private String orderNumber;
    private String reviewStatus;
    private String productName;
    private String reviewContents;
    private String userEmail;
    private String userName;
    private Timestamp reviewCreatedAt;

    @Builder
    public ReviewResDto(int num, Long productId, Long reviewId, String orderNumber, String reviewStatus, String productName, String reviewContents, String userEmail, String userName, Timestamp reviewCreatedAt) {
        this.num = num;
        this.productId = productId;
        this.reviewId = reviewId;
        this.orderNumber = orderNumber;
        this.reviewStatus = reviewStatus;
        this.productName = productName;
        this.reviewContents = reviewContents;
        this.userEmail = userEmail;
        this.userName = userName;
        this.reviewCreatedAt = reviewCreatedAt;
    }
}
