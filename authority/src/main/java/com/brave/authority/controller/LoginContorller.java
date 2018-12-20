package com.brave.authority.controller;

import com.core.response.ResponseJSONResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class LoginContorller {

    /**
     * ajax登录请求
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="login",method= {RequestMethod.POST,RequestMethod.OPTIONS})
    public ResponseJSONResult submitLogin(String username, String password, Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        try {

//            ShiroToken token = new ShiroToken(username, password);
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            Subject subject= SecurityUtils.getSubject();
            subject.login(token);
            resultMap.put("token", subject.getSession().getId());
            return   ResponseJSONResult.ok(resultMap);
        }catch (IncorrectCredentialsException ex) {
            return ResponseJSONResult.errorMsg("用户名密码不匹配");
        } catch (UnknownAccountException e){
            return ResponseJSONResult.errorMsg("用户名没有找到");
        } catch (AuthenticationException e) {
            return ResponseJSONResult.errorMsg("未授权");
        } catch (Exception e) {
            return ResponseJSONResult.errorMsg(e.getMessage());
        }
    }
    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    public ResponseJSONResult unLogin() {
        return ResponseJSONResult.NOT_LOGIN();
    }
    @RequestMapping(value = "/403")
    public ResponseJSONResult unauth() {
        return ResponseJSONResult.NOT_AUTHORIZATION();
    }

}
