package com.brave.authority.entity;

import com.core.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class AcPermission extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;//主键.
    private String name;//名称.
    @Column(columnDefinition="enum('menu','button','request')")
    private String resourceType;//资源类型，[view|request]
    private String url;//资源路径.
    private String permission; //权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
    private Integer parentId; //父编号
    private Boolean available = Boolean.FALSE;
    @ManyToMany
    @JoinTable(name="SysRolePermission",joinColumns={@JoinColumn(name="permissionId")},inverseJoinColumns={@JoinColumn(name="roleId")})
    @JsonIgnore
    private Set<AcRole> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<AcRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AcRole> roles) {
        this.roles = roles;
    }
}