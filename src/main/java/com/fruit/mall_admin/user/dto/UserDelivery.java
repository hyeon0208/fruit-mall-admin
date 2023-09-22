package com.fruit.mall_admin.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDelivery {
    private String deliveryName;
    private String userName;
    private String phoneNumber;
    private String zipcode;
    private String address;
}
