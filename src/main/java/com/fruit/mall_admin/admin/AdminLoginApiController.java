package com.fruit.mall_admin.admin;

import com.fruit.mall_admin.config.SessionAdmin;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.fruit.mall_admin.config.interceptor.LoginCheckInterceptor.LOGIN_ADMIN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminLoginApiController {
    private final AdminService adminService;

    @PostMapping("/login")
    public String loginConfirm(@RequestBody Admin inputData, HttpServletRequest request) {
        Admin loginAdmin = adminService.selectAdminById(inputData.getAdminId());
        if (!adminService.loginCheck(inputData.getAdminId(), inputData.getPassword(), loginAdmin)) {
            return "fail";
        }
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_ADMIN, new SessionAdmin(loginAdmin));
        return "success";
    }
}
