package com.marufhasan.UnitTest.service;


import com.marufhasan.UnitTest.dto.CreateUserDTO;
import com.marufhasan.UnitTest.model.User;
import com.marufhasan.UnitTest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(CreateUserDTO createUserDTO) {
        User user = toUser(createUserDTO);
        user = userRepository.save(user);
        return user;
    }

    private User toUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setUserName(createUserDTO.getUserName());
        user.setUserPassword(createUserDTO.getUserPassword());
        user.setUserEmail(createUserDTO.getUserEmail());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUserName(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        return user.orElse(null);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> savedUserOptional = userRepository.findById(user.getId());
        if(savedUserOptional.isEmpty())
        {
            return null;
        }
        User savedUser = savedUserOptional.get();
        savedUser.setUserName(user.getUserName());
        savedUser.setUserPassword(user.getUserPassword());
        savedUser.setUserEmail(user.getUserEmail());
        return userRepository.save(savedUser);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
