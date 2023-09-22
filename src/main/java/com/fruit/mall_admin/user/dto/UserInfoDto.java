package com.fruit.mall_admin.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class UserInfoDto {
    private Long userIdNo;
    private int num;
    private String userStatus;
    private String userName;
    private String userEmail;
    private int orderCount;
    private Timestamp userCreatedAt;
}
