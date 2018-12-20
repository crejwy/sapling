package com.brave.authority.service.serviceImpl;

import com.brave.authority.dao.UserRepository;
import com.brave.authority.entity.AcUser;
import com.brave.authority.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends AbstractBaseService<AcUser,Integer> implements UserInfoService{

    private UserRepository userRepository;

    @Autowired
    public UserInfoServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
        super.respository= userRepository;
    }

    @Override
    public AcUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
