package com.bp.getway.controller;

import com.bp.common.bean.ReturnBean;
import com.bp.common.entity.LoginUser;
import com.bp.common.enum_.CredentialType;
import com.bp.common.interface_.SystemClientInfo;
import com.bp.common.utils.UserUtils;

import com.bp.getway.client.Oauth2Client;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 登陆、刷新token、退出
 *
 * @author 钟欣凯
 */
@Slf4j
@RestController
public class TokenController {

    @Resource
    private Oauth2Client oauth2Client;

    /**
     * 系统登陆<br>
     * 根据用户名登录<br>
     * 采用oauth2密码模式获取access_token和refresh_token
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @PostMapping("/sys/login")
    public Map<String, Object> login(String username, String password) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(OAuth2Utils.GRANT_TYPE, "password");
        parameters.put(OAuth2Utils.CLIENT_ID, SystemClientInfo.CLIENT_ID);
        parameters.put("client_secret", SystemClientInfo.CLIENT_SECRET);
        parameters.put(OAuth2Utils.SCOPE, SystemClientInfo.CLIENT_SCOPE);
        parameters.put("username", username + "|" + CredentialType.USERNAME.name());
        parameters.put("password", password);

        return oauth2Client.postAccessToken(parameters);
    }




    /**
     * 系统刷新refresh_token
     *
     * @param refreshToken
     * @return
     */
    @PostMapping("/sys/refreshToken")
    public Map<String, Object> refreshToken(String refreshToken) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(OAuth2Utils.GRANT_TYPE, "refresh_token");
        parameters.put(OAuth2Utils.CLIENT_ID, SystemClientInfo.CLIENT_ID);
        parameters.put("client_secret", SystemClientInfo.CLIENT_SECRET);
        parameters.put(OAuth2Utils.SCOPE, SystemClientInfo.CLIENT_SCOPE);
        parameters.put("refresh_token", refreshToken);
        return oauth2Client.postAccessToken(parameters);
    }

    /**
     * 退出
     *
     * @param accessToken token
     */
    @GetMapping("/sys/logout")
    public void logout(String accessToken, @RequestHeader(required = false, value = "Authorization") String token) {
        if (StringUtils.isBlank(accessToken)) {
            if (StringUtils.isNoneBlank(token)) {
                accessToken = token.substring(OAuth2AccessToken.BEARER_TYPE.length() + 1);
            }
        }
        oauth2Client.removeToken(accessToken);
    }

    /**
     *  获取当前登录用户的信息
     * @return
     */
    @GetMapping("sys/getcurrentUserInfo")
    public ReturnBean getcurrentUserInfo(){
        LoginUser currentUser = UserUtils.getCurrentUser();
        return ReturnBean.ok(currentUser);
    }
}
