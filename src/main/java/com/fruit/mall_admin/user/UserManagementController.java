package com.fruit.mall_admin.user;

import com.fruit.mall_admin.user.dto.UserInfoDto;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserManagementController {
    private final UserService userService;

    @GetMapping("/member")
    public String test(Model model,
                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        PageInfo<UserInfoDto> userInfos = userService.selectUserInfo(pageNum, pageSize);
        model.addAttribute("userInfos", userInfos);
        model.addAttribute("count", userService.countUsersByStatus());
        return "admin/member";
    }
}
