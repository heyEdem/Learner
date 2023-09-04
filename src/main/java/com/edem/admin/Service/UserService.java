package com.edem.admin.Service;

import com.edem.admin.entity.User;

public interface UserService {
    User loadUserByEmail(String email);
    User createUser(String email, String password);
    void assignRolesToUsers(String email, String roleName);

}
