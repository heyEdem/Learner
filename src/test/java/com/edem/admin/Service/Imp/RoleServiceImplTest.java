package com.edem.admin.Service.Imp;

import com.edem.admin.dao.RoleDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleDao roleDao;

    @Mock
    private UserServiceImpl userService;

    @Test
    void testLoadRoleByName() {
        String roleName = "Admin";
        roleService.loadRoleByName(roleName);
        verify(roleDao).findByName(any());
    }

    @Test
    void testCreateRole() {
        String roleName = "Admin";
        roleService.createRole(roleName);
        verify(roleDao).save(any());
    }
}