package com.fruit.mall_admin.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    private final static String LOGIN_ADMIN = "loginAdmin";

    @GetMapping("/{pageName}")
    public String goSubPage(@PathVariable String pageName) {
        return "admin/" + pageName;
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        if (session.getAttribute(LOGIN_ADMIN) != null) {
            return "redirect:/admin/dashboard";
        }
        return "admin/index";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute(LOGIN_ADMIN) != null) {
            session.invalidate();
        }
        return "redirect:/admin/login";
    }
}
