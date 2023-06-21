package com.edem.admin.utility;

import com.edem.admin.dao.UserDao;

public class OperationUtility {

    public static void usersOperation(UserDao userDao){
        createUsers(userDao);
    }
}
