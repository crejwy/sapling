package com.brave.authority.entity;

import com.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class AcRole extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    private Integer id; // 编号

    @Column(unique =true)
    private String name; // 角色标识程序中判断使用,如"admin",这个是唯一的:


    private String description; // 角色描述,UI界面显示使用

    @Column
    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

    //角色 -- 权限关系：多对多关系;
    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="permissionId")})
    @JsonIgnore
    private Set<AcPermission> permissions;

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="uid")})
    @JsonIgnore
    private Set<AcUser> users=new HashSet<>();// 一个角色对应多个用户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return name;
    }

    public void setRole(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<AcPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<AcPermission> permissions) {
        this.permissions = permissions;
    }



    public Set<AcUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AcUser> users) {
        this.users = users;
    }
}