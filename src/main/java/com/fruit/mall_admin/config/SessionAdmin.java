package com.fruit.mall_admin.config;

import com.fruit.mall_admin.admin.Admin;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionAdmin implements Serializable {
    private String adminId;

    public SessionAdmin(Admin admin) {
        this.adminId = admin.getAdminId();
    }
}
