package com.brave.authority.service;

import com.brave.authority.entity.AcPermission;
import com.brave.authority.pojo.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService extends  BaseService<AcPermission,Integer> {

    List<Permission> queryAllPermissionAndMarkByRole(Integer roleId);
    List<Permission> queryMenuByRoles(List<Integer> roleIds);
}
