package com.example.security.controller;

import com.example.security.service.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class LoginController {
    @Resource
    private IUserService userService;
    @RequestMapping("/login1")
    public String login1(){
        System.out.println("登录后。。。");
        userService.getUser();
        return "login1";
    }
    @RequestMapping("/mylogin")
    public String mylogin(){
        System.out.println("mylogin");
        return "mylogin";
    }
    @RequestMapping("/user")
    public String user(Model model){
        model.addAttribute("role",getRole());
        model.addAttribute("user",getUsername());
        return "user";
    }
    @RequestMapping("/admin")
    public String admin(Model model){
        model.addAttribute("role",getRole());
        model.addAttribute("user",getUsername());
        return "admin";
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login?logout";
    }
    @RequestMapping("/accessDenied")
    public String accessDenied(Model model){
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUsername());
        return "accessDenied";
    }
    public String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    public String getRole(){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }
        System.out.println("roles = " + roles);
        return roles.toString();
    }
}
