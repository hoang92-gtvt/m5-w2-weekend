package com.module.case4.service;

import com.module.case4.model.users.Role;
import com.module.case4.model.users.RoleName;

import java.util.Optional;

public interface IRoleService extends IGeneralService<Role>{
    Optional<Role> findByName(RoleName name);
}
