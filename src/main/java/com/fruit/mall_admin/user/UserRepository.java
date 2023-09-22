package com.fruit.mall_admin.user;

import com.fruit.mall_admin.user.UserMapper;
import com.fruit.mall_admin.user.dto.UserCountDto;
import com.fruit.mall_admin.user.dto.UserDelivery;
import com.fruit.mall_admin.user.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository implements UserMapper {
    private final UserMapper userMapper;

    @Override
    public List<UserInfoDto> selectUserInfo() {
        return userMapper.selectUserInfo();
    }

    @Override
    public List<UserDelivery> selectDeliveriesByUserId(Long userIdNo) {
        return userMapper.selectDeliveriesByUserId(userIdNo);
    }

    @Override
    public UserCountDto countUsersByStatus() {
        return userMapper.countUsersByStatus();
    }

    @Override
    public void updateUserStatusByUserIdNo(Long userIdNo) {
        userMapper.updateUserStatusByUserIdNo(userIdNo);
    }
}
