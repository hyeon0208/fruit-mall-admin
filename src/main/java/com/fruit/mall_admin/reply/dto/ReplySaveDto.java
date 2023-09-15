package com.fruit.mall_admin.reply.dto;

import com.fruit.mall_admin.reply.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplySaveDto {
    private String reviewContents;
    private Long reviewId;

    public ReplySaveDto(String reviewContents, Long reviewId) {
        this.reviewContents = reviewContents;
        this.reviewId = reviewId;
    }

    public Reply toEntity(ReplySaveDto dto) {
        return Reply.builder()
                .adminId("guswns0208")
                .reviewId(dto.reviewId)
                .comments(dto.getReviewContents())
                .build();
    }
}
