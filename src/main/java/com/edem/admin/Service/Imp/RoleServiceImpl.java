package com.edem.admin.Service.Imp;

import com.edem.admin.Service.RoleService;
import com.edem.admin.dao.RoleDao;
import com.edem.admin.entity.Role;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role loadRoleByName(String roleName) {
        return roleDao.findByName(roleName);
    }

    @Override
    public Role createRole(String roleName) {
        return roleDao.save(new Role(roleName));
    }
}
