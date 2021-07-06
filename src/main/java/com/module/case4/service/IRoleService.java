package com.module.case4.service;

import com.module.case4.model.user.Role;
import com.module.case4.model.user.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
