package com.brave.authority.entity;

import com.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class AcUser extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    private Integer uid;
    @Column(unique =true,updatable = false)
    private String username;//帐号
    private String password; //密码;
    private String salt;//加密密码的盐
//    @Column(columnDefinition="enum('1','2','3')")
    private byte state =1 ;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定
    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "roleId") })
    private List<AcRole> roleList;// 一个用户具有多个角色

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

    public List<AcRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<AcRole> roleList) {
        this.roleList = roleList;
    }


    public String getCredentialsSalt(){
        return username+salt;
    }
    public String getRoles(){
        String rs="";
        if(roleList!=null&&roleList.size()>0){
            rs=roleList.stream().map(r->r.getRole()).collect(Collectors.joining(","));
        }
        return rs;
    }
    public String getRoleIds(){
        String rs="";
        if(roleList!=null&&roleList.size()>0){
            rs=roleList.stream().map(r->r.getId().toString()).collect(Collectors.joining(","));
        }
        return rs;
    }
}