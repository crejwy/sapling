package com.brave.authority.dao;

import com.brave.authority.entity.AcPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<AcPermission,Integer> {
}
