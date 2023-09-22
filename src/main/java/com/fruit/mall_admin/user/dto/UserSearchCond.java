package com.fruit.mall_admin.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserSearchCond {
    private String userStatus;
    private String userSearchCategory;
    private String userSearchText;
    private int userPageNum;
    private int userPageSize;
}
