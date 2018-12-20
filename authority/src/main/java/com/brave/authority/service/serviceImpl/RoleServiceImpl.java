package com.brave.authority.service.serviceImpl;

import com.brave.authority.dao.RoleRepository;
import com.brave.authority.entity.AcRole;
import com.brave.authority.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends AbstractBaseService<AcRole,Integer>  implements  RoleService{
    private RoleRepository roleRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.respository= roleRepository;
    }



}
