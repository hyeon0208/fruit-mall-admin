package com.fruit.mall_admin.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    Admin selectAdminById(@Param("adminId") String adminId);
}
