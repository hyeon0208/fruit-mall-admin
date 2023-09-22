package com.fruit.mall_admin.user;

import com.fruit.mall_admin.review.dto.ReviewResDto;
import com.fruit.mall_admin.review.dto.ReviewSearchCond;
import com.fruit.mall_admin.user.dto.UserDelivery;
import com.fruit.mall_admin.user.dto.UserInfoDto;
import com.fruit.mall_admin.user.dto.UserSearchCond;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserManagementApiController {
    private final UserService userService;

    @GetMapping("/deliveries/{userIdNo}")
    public ResponseEntity<?> getDeliveries(@PathVariable Long userIdNo) {
        List<UserDelivery> userDeliveries = userService.selectDeliveriesByUserId(userIdNo);

        if (userDeliveries.size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.selectDeliveriesByUserId(userIdNo));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody Map<String, Long> data) {
        userService.updateUserStatusByUserIdNo(data.get("userIdNo"));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/member/search")
    public PageInfo<UserInfoDto> memberSearchFilter(@ModelAttribute UserSearchCond cond) {
        PageInfo<UserInfoDto> usersBySearchCond = userService.getUsersBySearchCond(cond);
        return usersBySearchCond;
    }
}
