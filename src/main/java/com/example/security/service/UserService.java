package com.example.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements IUserService, UserDetailsService {
//    private static final Log LOG = LogFactory.getLog(UserService.class);
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    public void getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String password = principal.getPassword();
        System.out.println("password = " + password);
        String username = principal.getUsername();
        System.out.println("username = " + userName);
        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String a = authority.getAuthority();
            System.out.println("权限 = " + a);
        }
        System.out.println("service中取到的用户名" + userName);
    }

    /**
     * 从数据库中查询用户名，生成user对象
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("s = " + s);
        UserDo userDo = new UserDo();
        userDo.setLoginname(s);
        userDo = userDo.getUserByLoginName();
        System.out.println(userDo.getRoles().size());
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<RoleDo> roles = userDo.getRoles();
        for (RoleDo role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
            System.out.println("role = " + role.getAuthority());
            LOG.info("role = " + role.getAuthority());
        }
        return new User(userDo.getUsername(), userDo.getPassword(), authorities);
    }
}
