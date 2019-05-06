package com.bp.oauth.service.impl;

import com.bp.common.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 根据用户名获取用户<br>
 * <p>
 * 密码校验请看下面两个类
 * @author 钟欣凯
 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
 * @see org.springframework.security.authentication.dao.DaoAuthenticationProvider
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       // todo 访问数据库获取用户实体
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername("admin");
        Set<String>per = new HashSet<>();
        per.add("sys:selectList");
        per.add("sys:insert");
        per.add("sys:update");
        per.add("sys:delete");
        loginUser.setPassword(passwordEncoder.encode("123456"));
        loginUser.setPermissions(per);
        Set<String>role = new HashSet<>();
        role.add("ROLE_ADMIN");
        role.add("ROLE_USER");
        loginUser.setSysRoles(role);
        loginUser.setEnabled(true);
        return loginUser;
    }


}
