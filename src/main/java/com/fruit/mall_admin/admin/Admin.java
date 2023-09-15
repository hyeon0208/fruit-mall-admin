package com.fruit.mall_admin.admin;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    private String adminId;
    private String password;

    @Builder
    public Admin(String adminId, String password) {
        this.adminId = adminId;
        this.password = password;
    }
}
