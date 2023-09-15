package com.fruit.mall_admin.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewCountDto {
    private int totalReviewCount;
    private int completedRepliedCount;
    private int notRepliedCount;

    public ReviewCountDto(int totalReviewCount, int completedRepliedCount, int notRepliedCount) {
        this.totalReviewCount = totalReviewCount;
        this.completedRepliedCount = completedRepliedCount;
        this.notRepliedCount = notRepliedCount;
    }
}
