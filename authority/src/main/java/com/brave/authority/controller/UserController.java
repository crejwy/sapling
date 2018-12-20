package com.brave.authority.controller;

import com.alibaba.fastjson.JSON;
import com.brave.authority.entity.AcRole;
import com.brave.authority.entity.AcUser;
import com.brave.authority.pojo.Permission;
import com.brave.authority.pojo.Role;
import com.brave.authority.pojo.User;
import com.brave.authority.service.PermissionService;
import com.brave.authority.service.UserInfoService;
import com.core.controller.BaseController;
import com.core.response.ResponseJSONResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController  extends BaseController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private Sid sid;

    @RequestMapping(value = "queryUserByToken",method = RequestMethod.GET)
    public User queryUserByToken(){
        User u= new User();
        AcUser acUser= (AcUser)CurrentUser();
        BeanUtils.copyProperties(acUser,u,"roleList");
        for(AcRole role:acUser.getRoleList()){
            Role role1=new Role();
            BeanUtils.copyProperties(role,role1,"roleList");
            u.getRoleList().add(role1);
        }
        List<Permission> permissions=permissionService.queryMenuByRoles(u.getRoleList().stream().map(r->r.getId()).collect(Collectors.toList()));
        u.setPermissions(permissions);
        return  u;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    @RequiresPermissions("user:view")
    public List<AcUser> queryUserList(){
        List<AcUser> users=userInfoService.queryList();
        return  users;
    }

    @RequestMapping(value = "",method = RequestMethod.DELETE)
    @RequiresPermissions("user:delete")
    public ResponseJSONResult deleteUser(Integer uid){
        userInfoService.delete(uid);
        return  ResponseJSONResult.ok();
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    @RequiresPermissions("user:create")
    public AcUser create(AcUser user){
        Date now=new Date();
        AcUser u= (AcUser) CurrentUser();
        user.setUid(null);
        user.setSalt(sid.nextShort());
        user.setCreatedt(now);
        user.setModifydt(now);
        user.setCreator(u.getUsername());
        user.setModifier(u.getUsername());

        return userInfoService.saveOrUpdate(user);
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    @RequiresPermissions("user:update")
    public AcUser updateUser(AcUser user){
        Date now=new Date();
        AcUser u= (AcUser) CurrentUser();
        AcUser lu=userInfoService.findById(user.getUid());
        BeanUtils.copyProperties(user,lu,"roleList");
        lu.setSalt(sid.nextShort());
        lu.setModifydt(now);
        lu.setModifier(u.getUsername());
        return userInfoService.saveOrUpdate(lu);
    }

    @RequestMapping(value = "alterpassword",method = RequestMethod.POST)
    @RequiresPermissions("user:alterpassword")
    public ResponseJSONResult alterPassword(String username, String oldpassword, String newpassword){
        Date now=new Date();
        AcUser u= (AcUser) CurrentUser();
        AcUser au=userInfoService.findByUsername(username);
        if(!au.getPassword().equals(oldpassword)){
            return  ResponseJSONResult.errorMsg("原始密码输入错误！");
        }
        au.setPassword(newpassword);
        au.setModifydt(now);
        au.setModifier(u.getUsername());
        return ResponseJSONResult.ok(userInfoService.saveOrUpdate(au));
    }
    @RequestMapping(value = "assignroles",method = RequestMethod.POST)
    @RequiresPermissions("user_role:save")
    public ResponseJSONResult assignRoles(@RequestBody AcUser user){
        return ResponseJSONResult.ok(userInfoService.saveOrUpdate(user));
    }

}
