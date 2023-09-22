package com.fruit.mall_admin.user;

import com.fruit.mall_admin.review.dto.ReviewResDto;
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
        for (UserInfoDto user : userInfos) {
            Long userIdNo = user.getUserIdNo();
            user.setDeliveries(userRepository.selectDeliveriesByUserId(userIdNo));
        }
        return new PageInfo<>(userInfos);
    }
}
