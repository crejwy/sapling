package com.brave.authority.pojo;

import com.core.base.BaseEntity;
import com.core.base.BasePojo;
import com.core.utils.Collectors;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity {
    private Integer uid;
    private String username;//帐号
    private String password; //密码;
    private String salt;//加密密码的盐
    private byte state =1 ;//用户状态,0:创建未认证
    private List<Role> roleList=new ArrayList<>();
    private List<Permission> permissions=new ArrayList<>();

    public List<Permission> getPermissions() {
        return permissions;
    }
    public List<Permission> getRouters() {
        List<Permission>  permissionTree=new ArrayList<>();
        if(this.permissions.size()>0){
            List<Permission> routers=this.permissions.stream().filter(p->p.getResourceType().equals("menu")).collect(java.util.stream.Collectors.toList());
            permissionTree= Collectors.toTree(routers);
        }

        return   permissionTree;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
