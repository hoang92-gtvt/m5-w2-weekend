package com.module.case4.repository;

import com.module.case4.model.user.Role;
import com.module.case4.model.user.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
