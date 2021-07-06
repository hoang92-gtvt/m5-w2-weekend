package com.module.case4.service.impl;

import com.module.case4.model.user.Role;
import com.module.case4.model.user.RoleName;
import com.module.case4.model.user.User;
import com.module.case4.repository.IRoleRepository;
import com.module.case4.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RoleService implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
