package com.example.config;

import com.example.controller.MyController;
import com.example.pojo.User;
import com.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * Created by chenzhipeng on 2020/5/13 23:16
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    private static final transient Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("执行了授权");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //拿到当前登录的用户这个对象
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        User  user = (User) principal;
        simpleAuthorizationInfo.addStringPermission(user.getPrams());
        logger.info("授权中user = "+user.toString());
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("执行了认证");
        //获取当前的用户对象
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //连接真实数据库
        User user = userService.queryUserByName(token.getUsername());
        if(user == null){   //用户名不存在
            return null;   // throws UnknownAccountException
        }
        //密码认证
        return new SimpleAuthenticationInfo(user, user.getPwd(),"");


    }
}
