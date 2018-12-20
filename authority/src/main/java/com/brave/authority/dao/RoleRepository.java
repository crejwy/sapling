package com.brave.authority.dao;

import com.brave.authority.entity.AcRole;
import com.brave.authority.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<AcRole,Integer> {
}