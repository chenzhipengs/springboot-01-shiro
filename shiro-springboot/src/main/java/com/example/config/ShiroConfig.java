package com.example.config;


import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by chenzhipeng on 2020/5/13 22:52
 * Shiro的配置类
 */

@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager securityManager){
        //Shiro Filter.
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro的内置过滤器
        Map<String, String> filterMap = new LinkedHashMap<>();
        /**
         * anon:所有 url 都都可以匿名访问
         * authc: 需要认证才能进行访问
         * user:配置记住我或认证通过可以访问
         * perms:拥有对某个资源的权限才能访问
         * role:拥有某个角色权限才能访问
         */

        filterMap.put("/user/*","authc");

        //授权
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");


        //设置登录界面
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        //设置未
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurityManager
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //创建一个Realm,自定义类1
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
