package com.example.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chenzhipeng on 2020/5/13 22:22
 */
@Controller
public class MyController {
    private static final transient Logger log = LoggerFactory.getLogger(MyController.class);

    @RequestMapping(value = {"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello,shiro");
        log.info("调用了toIndex方法");
        return "index";
    }

    @RequestMapping(value = {"/tologin"})
    public String toLogin(Model model){
        model.addAttribute("msg","hello,shiro");
        return "login";
    }

    @RequestMapping(value = {"/user/add"})
    public String add(Model model){
        model.addAttribute("msg","hello,shiro");
        return "user/add";
    }

    @RequestMapping(value = {"/user/update"})
    public String update(Model model){
        model.addAttribute("msg","hello,shiro");
        return "user/update";
    }

    @RequestMapping(value = {"/login"})
    public String login(String username,String password,Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //生成认证的Token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            //执行登录逻辑
            subject.login(token);
            return "index";
        } catch (UnknownAccountException uae) {
            log.info("There is no user with username of " + token.getPrincipal());
            model.addAttribute("msg","用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException ice) {
            log.info("Password for account " + token.getPrincipal() + " was incorrect!"); //获取了用户名
            model.addAttribute("msg","密码错误");
            return "login";
        } catch (LockedAccountException lae) {
            log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
            model.addAttribute("msg","账号被锁定");
            return "login";
        }
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorized(){
        return "未授权";
    }


}
