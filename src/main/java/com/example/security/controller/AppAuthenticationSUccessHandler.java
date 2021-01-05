package com.example.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class AppAuthenticationSUccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    //spring security 通过RedirectStrategy对象负责所有重定向服务
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    /**
     * 重写handler方法，方法中redirectStrategy重定向到新的URL路径
     */
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {
        String url = getUrl(authentication);
        redirectStrategy.sendRedirect(request, response, url);
    }

    private String getUrl(Authentication authentication) {
        String url = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }
        //判断不同的角色跳转到不同的路径/页面
        if (isAdmin(roles)){
            url = "/admin";
        } else if(isUser(roles)){
            url = "/user";
        }else{
            url = "/accessDenied";
        }
        return url;
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("ROLE_USER"))
            return true;
        return false;
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN"))
            return true;
        return false;
    }
}
