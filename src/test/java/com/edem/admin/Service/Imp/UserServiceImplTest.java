package com.edem.admin.Service.Imp;

import com.edem.admin.dao.RoleDao;
import com.edem.admin.dao.UserDao;
import com.edem.admin.entity.Role;
import com.edem.admin.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserDao userDao;
    @Mock
    private RoleDao roleDao;
    @Mock
    private User mockedUser;
    @Test
    void testLoadUserByEmail() {
        userService.loadUserByEmail("user@gmail.com");
        verify(userDao, times(1)).findByEmail(any());
    }

    @Test
    void testCreateUser() {
        String email = "user@gmail.com";
        String password = "pass";

        User user = new User(email,password);
        userService.createUser(email, password);

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userDao).save(argumentCaptor.capture());
        User captured = argumentCaptor.getValue();

        assertEquals(user,captured);
    }

    @Test
    void testAssignRolesToUsers() {
        Role role  = new Role();
        role.setRoleId(1L);

        when(userDao.findByEmail(any())).thenReturn(mockedUser);
        when(roleDao.findByName(any())).thenReturn(role);

        userService.assignRolesToUsers("user@gmail.com","Admin");
        verify(mockedUser, times(1)).assignRoleToUser(role);
    }
}