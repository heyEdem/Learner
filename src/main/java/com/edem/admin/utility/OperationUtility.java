package com.edem.admin.utility;

import com.edem.admin.dao.RoleDao;
import com.edem.admin.dao.UserDao;
import com.edem.admin.entity.Role;
import com.edem.admin.entity.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class OperationUtility {

    public static void usersOperation(UserDao userDao){
        createUsers(userDao);
        updateUsers(userDao);
        
    }

    public static void roleOperations(RoleDao roleDao){
        createRoles(roleDao);
        updateRoles(roleDao);
        deleteRoles(roleDao);
        fetchRoles(roleDao);
    }


    private static void createUsers(UserDao userDao) {
        User user1 = new User("u1@gmail.com","pass1");
        userDao.save(user1);

        User user2 = new User("u2@gmail.com","pass2");
        userDao.save(user2);

        User user3 = new User("u3@gmail.com","pass2");
        userDao.save(user3);
    }


    private static void updateUsers(UserDao userDao){
        User user = userDao.findById(2L).orElseThrow(()->new EntityNotFoundException("User not found"));
    }

    private static void fetchRoles(RoleDao roleDao) {
        roleDao.findAll().forEach(role-> System.out.println(role.toString()));
    }

    private static void deleteRoles(RoleDao roleDao) {
        roleDao.deleteById(2L);
    }

    private static void updateRoles(RoleDao roleDao) {
        Role role = roleDao.findById(1L).orElseThrow(()->new EntityNotFoundException("Not found"));
        role.setName("New Admin");
        roleDao.save(role);

    }

    private static void createRoles(RoleDao roleDao) {
        Role role1 = new Role("Admin");
        roleDao.save(role1);

        Role role2 = new Role("Instructor");
        roleDao.save(role2);

        Role role3 = new Role("Student");
        roleDao.save(role3);
    }

    public static void assignRolesToUsers (UserDao userDao, RoleDao roleDao){
        Role role =roleDao.findByName("Admin");
        if (role==null) throw new EntityNotFoundException("Role Not Found");

        List<User> users = userDao.findAll();
        users.forEach( user-> {
            user.assignRoleToUser(role);
            userDao.save (user);
        });
    }

}
