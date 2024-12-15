package com.marufhasan.UnitTest.controller;


import com.marufhasan.UnitTest.dto.CreateUserDTO;
import com.marufhasan.UnitTest.model.User;
import com.marufhasan.UnitTest.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public User createUser(@Valid @RequestBody CreateUserDTO createUserDTO)
    {
        return userService.createUser(createUserDTO);
    }

    @GetMapping("")
    public List<User> getAllUsers()
    {
        return null;
    }
    @GetMapping("{userName}")
    public User getUserByUserName(@PathVariable String userName)
    {
        return null;
    }

    @PutMapping("")
    public User updateUser(@Valid @RequestBody User user)
    {
        return null;
    }

    @DeleteMapping("/")
    public void deleteUser(String userName)
    {
        return ;
    }
}
