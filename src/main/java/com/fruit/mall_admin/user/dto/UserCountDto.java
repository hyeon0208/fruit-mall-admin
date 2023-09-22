package com.fruit.mall_admin.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCountDto {
    private int totalUserCount;
    private int activeCount;
    private int withdrawCount;
}
