package com.brave.authority.dao;

import com.brave.authority.entity.AcUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AcUser,Integer> {
    AcUser findByUsername(String name);
}
