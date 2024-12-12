package com.marufhasan.UnitTest.service;

import com.marufhasan.UnitTest.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> getAllUsers();

    User getUserByUserName(String userName);

    User updateUser(User user);

    void deleteUser(String userName);
}
