package com.brave.authority.service.serviceImpl;

import com.brave.authority.dao.PermissionRepository;
import com.brave.authority.entity.AcPermission;
import com.brave.authority.pojo.Permission;
import com.brave.authority.service.PermissionService;
import com.core.utils.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl extends  AbstractBaseService<AcPermission,Integer> implements PermissionService {
    private PermissionRepository permissionRepository;
    @Autowired
    public  PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository=permissionRepository;
        this.respository= permissionRepository;
    }


    @Override
    public List<Permission> queryAllPermissionAndMarkByRole(Integer roleId) {
        List<AcPermission> allPermissions=permissionRepository.findAll();
        List<Permission>  permissions=new ArrayList<>();
        for(AcPermission r : allPermissions) {
            Permission rr=new Permission();
            permissions.add(rr);
            rr.setId(r.getId());
            rr.setName(r.getName());
            rr.setPermission(r.getPermission());
            rr.setAvailable(r.getAvailable());
            rr.setUrl(r.getUrl());
            rr.setResourceType(r.getResourceType());
            rr.setParentId(r.getParentId());
            rr.setChildren(new ArrayList<Permission>());
            rr.setChecked(r.getRoles().size()>0?r.getRoles().stream().filter(role->role.getId()==roleId).count()>0:false);
        }

        List<Permission>  permissionTree=new ArrayList<>();
        if(permissions.size()>0){
            permissionTree= Collectors.toTree(permissions);
        }

        return   permissionTree;
    }

    @Override
    public List<Permission> queryMenuByRoles(List<Integer> roleIds) {
        List<AcPermission> allPermissions=permissionRepository.findAll();
        List<Permission>  permissions=new ArrayList<>();
        for(AcPermission r : allPermissions) {
            Permission rr=new Permission();
            rr.setId(r.getId());
            rr.setName(r.getName());
            rr.setPermission(r.getPermission());
            rr.setAvailable(r.getAvailable());
            rr.setUrl(r.getUrl());
            rr.setResourceType(r.getResourceType());
            rr.setParentId(r.getParentId());
            rr.setChildren(new ArrayList<Permission>());
            rr.setChecked(r.getRoles().size()>0?r.getRoles().stream().filter(role->roleIds.contains(role.getId())).count()>0:false);
            if(rr.getChecked()==true){
                System.out.println(rr.getResourceType());
                System.out.println(rr.getResourceType().equals("menu"));
            }
            if(rr.getChecked()==true){
                permissions.add(rr);
            }
        }

        return  permissions;
    }
}
