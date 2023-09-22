package com.fruit.mall_admin.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
public class UserInfoDto {
    private Long userIdNo;
    private int num;
    private String userStatus;
    private String userName;
    private String userEmail;
    private int orderCount;
    private List<UserDelivery> deliveries;
    private Timestamp userCreatedAt;

    public void setDeliveries(List<UserDelivery> deliveries) {
        this.deliveries = deliveries;
    }
}
