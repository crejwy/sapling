package com.brave.authority.controller;

import com.brave.authority.entity.AcPermission;
import com.brave.authority.entity.AcRole;
import com.brave.authority.entity.AcUser;
import com.brave.authority.service.RoleService;
import com.core.controller.BaseController;
import com.core.response.ResponseJSONResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

//    @Autowired
//    private Sid sid;

    @RequestMapping(value = "",method = RequestMethod.GET)
    @RequiresPermissions("role:view")
    public List<AcRole> queryList(){
        return roleService.queryList();
    }

    @RequestMapping(value = "",method = RequestMethod.DELETE)
    @RequiresPermissions("role:delete")
    public ResponseJSONResult delete(Integer id){
        roleService.delete(id);
        return  ResponseJSONResult.ok();
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    @RequiresPermissions("role:create")
    public AcRole create(AcRole role){
        Date now=new Date();
        AcUser u= (AcUser) CurrentUser();
        role.setId(null);
        role.setIsdeleted(false);
        role.setAvailable(true);
        role.setCreatedt(now);
        role.setModifydt(now);
        role.setCreator(u.getUsername());
        role.setModifier(u.getUsername());
        return roleService.saveOrUpdate(role);
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    @RequiresPermissions("role:update")
    public AcRole update(AcRole role){
        Date now=new Date();
        AcUser u= (AcUser) CurrentUser();
        AcRole ar=roleService.findById(role.getId());
        BeanUtils.copyProperties(role,ar,"permissions","routers","users");
        ar.setModifydt(now);
        ar.setModifier(u.getUsername());
        return roleService.saveOrUpdate(ar);

    }

    @RequestMapping(value = "saveRolePermission" ,method = RequestMethod.POST)
    @RequiresPermissions("role_permission:save")
    public ResponseJSONResult saveRolePermission(int roleId,@RequestParam List<Integer> permissions){
        AcRole role=roleService.findById(roleId);

        if(role==null){
            return  ResponseJSONResult.errorMsg("角色不存在");
        }else{
            Set<AcPermission> permissionsSet=new HashSet<>();
            permissions.forEach(id->{
                AcPermission ar=new AcPermission();
                ar.setId(id);
                permissionsSet.add(ar);
            });
            if(!permissionsSet.isEmpty()){
                role.setPermissions(permissionsSet);
                roleService.saveOrUpdate(role);
                return  ResponseJSONResult.ok();
            }else{
                return  ResponseJSONResult.errorMsg("请选择权限");
            }

        }
    }
}
