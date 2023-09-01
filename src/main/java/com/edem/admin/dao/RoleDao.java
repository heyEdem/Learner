package com.edem.admin.dao;

import com.edem.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleDao extends JpaRepository<Role,Long> {

    Role findByName (String name);
}
