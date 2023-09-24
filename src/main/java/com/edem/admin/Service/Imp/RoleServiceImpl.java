package com.edem.admin.Service.Imp;

import com.edem.admin.Service.RoleService;
import com.edem.admin.dao.RoleDao;
import com.edem.admin.entity.Role;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

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
