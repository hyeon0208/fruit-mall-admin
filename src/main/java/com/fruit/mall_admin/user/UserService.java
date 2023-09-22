package com.fruit.mall_admin.user;

import com.fruit.mall_admin.review.dto.ReviewResDto;
import com.fruit.mall_admin.user.dto.UserCountDto;
import com.fruit.mall_admin.user.dto.UserDelivery;
import com.fruit.mall_admin.user.dto.UserInfoDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public PageInfo<UserInfoDto> selectUserInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, "NUM DESC");
        List<UserInfoDto> userInfos = userRepository.selectUserInfo();
        return new PageInfo<>(userInfos);
    }

    public List<UserDelivery> selectDeliveriesByUserId(Long userIdNo) {
        return userRepository.selectDeliveriesByUserId(userIdNo);
    }

    public UserCountDto countUsersByStatus() {
        return userRepository.countUsersByStatus();
    }

    public void updateUserStatusByUserIdNo(Long userIdNo) {
        userRepository.updateUserStatusByUserIdNo(userIdNo);
    }
}
