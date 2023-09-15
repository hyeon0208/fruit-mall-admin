package com.fruit.mall_admin.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class ReviewResDto {
    private int num;
    private String reviewStatus;
    private String productName;
    private String reviewContents;
    private String userEmail;
    private String userName;
    private Timestamp reviewCreatedAt;

    @Builder
    public ReviewResDto(int num, String reviewStatus, String productName, String reviewContents, String userEmail, String userName, Timestamp reviewCreatedAt) {
        this.num = num;
        this.reviewStatus = reviewStatus;
        this.productName = productName;
        this.reviewContents = reviewContents;
        this.userEmail = userEmail;
        this.userName = userName;
        this.reviewCreatedAt = reviewCreatedAt;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
