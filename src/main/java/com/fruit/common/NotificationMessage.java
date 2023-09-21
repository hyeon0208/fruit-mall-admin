package com.fruit.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationMessage {
    private Long reviewId;
    private String userId;
    private String message;
}
