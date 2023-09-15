package com.fruit.mall_admin.reply;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class Reply {
    private Long replyId;
    private String adminId;
    private Long reviewId;
    private String comments;
    private Timestamp createdAt;

    @Builder
    public Reply(String adminId, Long reviewId, String comments, Timestamp createdAt) {
        this.adminId = adminId;
        this.reviewId = reviewId;
        this.comments = comments;
        this.createdAt = createdAt;
    }
}
