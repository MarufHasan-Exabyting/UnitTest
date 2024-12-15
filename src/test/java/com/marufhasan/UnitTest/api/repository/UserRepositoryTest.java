package com.marufhasan.UnitTest.api.repository;

import com.marufhasan.UnitTest.model.User;
import com.marufhasan.UnitTest.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    @Autowired
    private  UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger("TestLogger");


  //  public UserRepositoryTest(UserRepository userRepository) {
   //     this.userRepository = userRepository;
   // }

    @Test
    public void createUser_ValidUserInfo_SaveUser()
    {
        //Arrange

        User user = new User("maruf","123456","marufhasan212@gmail.com");

        //Act
        User saveduser = userRepository.save(user);

        //Assert
        Assertions.assertNotNull(saveduser);
        Assertions.assertEquals(saveduser.getUserName(), user.getUserName());
        Assertions.assertTrue(saveduser.getId()>0);
        logger.info(saveduser.toString());
    }

    @Test
    public void getAllUsers_SavedAllUsers()
    {
        ArrayList<User> userList = new ArrayList<User>(
            List.of(new User("maruf1","123456","marufhasan212@gmail.com"),
            new User("maruf2","123456","marufhasan212@gmail.com"),
            new User("maruf3","123456","marufhasan212@gmail.com")));


        for(User user : userList)
        {
            userRepository.save(user);
        }

        List<User>savedList = userRepository.findAll();
        Assertions.assertNotNull(savedList);
        Assertions.assertEquals((int)savedList.size(), 3);
        Assertions.assertIterableEquals(userList, savedList);
    }

    @Test
    public void getUserByUserName_If_User_exists()
    {
        User user = new User("marufhasan","123456","marufhasan212@gmail.com");
        userRepository.save(user);

        Optional<User> userByUserName = userRepository.findByUserName("marufhasan");
        Assertions.assertTrue(userByUserName.isPresent());
        Assertions.assertEquals(user, userByUserName.get());
    }

    @Test
    public void UpdateUser_GivenUser()
    {
        User user = new User("marufhasan", "123456", "marufhasan212@gmail.com");
        userRepository.save(user);

        User savedUser = userRepository.findById(user.getId()).get();
        savedUser.setUserName("maruf");
        savedUser.setUserPassword("987654321");
        User updateduser = userRepository.save(savedUser);
        Assertions.assertNotNull(updateduser);
        Assertions.assertNotNull(updateduser.getUserName());
        Assertions.assertNotNull(updateduser.getUserPassword());
        Assertions.assertEquals("maruf",updateduser.getUserName());
        Assertions.assertEquals( "987654321",updateduser.getUserPassword());
    }

    @Test
    public void DeleteUser_GivenUserId_GetNull()
    {
        User user = new User("marufhasan", "123456", "marufhasan212@gmail.com");
        userRepository.save(user);

        userRepository.deleteById(user.getId());
        Optional<User> deletedUser = userRepository.findById(user.getId());
        Assertions.assertTrue(deletedUser.isEmpty());
    }
}
