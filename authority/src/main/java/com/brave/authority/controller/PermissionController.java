package com.brave.authority.controller;

import com.brave.authority.entity.AcPermission;
import com.brave.authority.entity.AcUser;
import com.brave.authority.pojo.Permission;
import com.brave.authority.service.PermissionService;
import com.core.controller.BaseController;
import com.core.response.ResponseJSONResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("permission")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @RequiresPermissions("permission:view")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<AcPermission> queryList() {
        return permissionService.queryList();
    }

    @RequiresPermissions("permission:delete")
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseJSONResult delete(Integer id) {
        permissionService.delete(id);
        return  ResponseJSONResult.ok();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @RequiresPermissions("permission:create")
    public AcPermission create(AcPermission permission) {
        Date now = new Date();
        AcUser u = (AcUser) CurrentUser();
        permission.setId(null);
        permission.setIsdeleted(false);
        permission.setAvailable(true);
        permission.setCreatedt(now);
        permission.setModifydt(now);
        permission.setCreator(u.getUsername());
        permission.setModifier(u.getUsername());
        return permissionService.saveOrUpdate(permission);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @RequiresPermissions("permission:update")
    public AcPermission update(AcPermission permission) {
        Date now = new Date();
        AcUser u = (AcUser) CurrentUser();
        AcPermission ap = permissionService.findById(permission.getId());
        BeanUtils.copyProperties(permission, ap, "roles");
        ap.setModifydt(now);
        ap.setModifier(u.getUsername());
        return permissionService.saveOrUpdate(ap);
    }

    @RequestMapping(value = "queryAllPermissionAndMarkByRole", method = RequestMethod.GET)
    @RequiresPermissions("role_permission:view")
    public List<Permission> queryAllPermissionAndMarkByRole(Integer roleId) {
        return permissionService.queryAllPermissionAndMarkByRole(roleId);
    }


}
