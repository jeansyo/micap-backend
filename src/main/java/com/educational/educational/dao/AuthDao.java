package com.educational.educational.dao;

import com.educational.educational.models.Users;

public interface AuthDao {
    Users verifyCrendentials(Users user);

    boolean createUser(Users user);

    Users getUserByID(String userID);
}
