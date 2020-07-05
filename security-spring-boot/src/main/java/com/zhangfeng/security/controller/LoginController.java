package com.zhangfeng.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
public class LoginController {

    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(){
        return getUserName()+"登录成功";
    }

    /**
     * 测试资源1
     * @return
     */
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(){
        return getUserName()+" 访问资源1";
    }

    /**
     * 测试资源2
     * @return
     */
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(){
        return getUserName()+" 访问资源2";
    }

    /**
     * 测试资源3
     * @return
     */
    @GetMapping(value = "/r/r3",produces = {"text/plain;charset=UTF-8"})
    public String r3(){
        return getUserName()+" 访问资源3";
    }

    /**
     * 测试资源4
     * @return
     */
    @GetMapping(value = "/r/r4",produces = {"text/plain;charset=UTF-8"})
    public String r4(){
        return getUserName()+" 访问资源4";
    }

    public String getUserName(){
        //拿到用户登录信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String userName = null;
        //如果用户没有登录，给一个匿名身份
        if(principal == null){
            userName = "匿名";
        }

        //如果用户登录就强转为userDetails
        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            userName = userDetails.getUsername();
        }else{
            userName = principal.toString();
        }
        return userName;
    }
}
