package com.marufhasan.UnitTest.service;

import com.marufhasan.UnitTest.dto.CreateUserDTO;
import com.marufhasan.UnitTest.model.User;

import java.util.List;

public interface UserService {
    User createUser(CreateUserDTO createUserDTO);

    List<User> getAllUsers();

    User getUserByUserName(String userName);

    User updateUser(User user);

    void deleteUser(long userId);
}
