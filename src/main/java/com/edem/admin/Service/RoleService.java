package com.edem.admin.Service;

import com.edem.admin.entity.Role;

public interface RoleService {
    Role loadRoleByName(String roleName);
    Role createRole(String roleName);

}
