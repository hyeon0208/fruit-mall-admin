package com.fruit.mall_admin.notifications.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class NotificationsDto {
    private String productName;
    private Long reviewId;

    public NotificationsDto(String productName, Long reviewId) {
        this.productName = productName;
        this.reviewId = reviewId;
    }
}
