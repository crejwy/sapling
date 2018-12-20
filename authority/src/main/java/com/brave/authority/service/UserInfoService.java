package com.brave.authority.service;

import com.brave.authority.entity.AcUser;

import java.util.List;

public interface UserInfoService extends  BaseService<AcUser,Integer> {
    AcUser findByUsername(String username);

}
