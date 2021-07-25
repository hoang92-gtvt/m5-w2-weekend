package com.module.case4.repository;

import com.module.case4.model.users.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

}
