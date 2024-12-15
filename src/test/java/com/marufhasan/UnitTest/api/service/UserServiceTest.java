package com.marufhasan.UnitTest.api.service;


import com.marufhasan.UnitTest.dto.CreateUserDTO;
import com.marufhasan.UnitTest.model.User;
import com.marufhasan.UnitTest.repository.UserRepository;
import com.marufhasan.UnitTest.service.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void createUser_givenUserDTO_returnSavedUser()
    {
        //Given
        CreateUserDTO createUserDTO = new CreateUserDTO("marufhasan","123456","marufhasan212@gmail.com");

        User user = new User("marufhasan", "123456", "marufhasan212@gmail.com");

        //Mock the calls
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        //when(userService.ToUser(createUserDTO)).thenReturn(user);

        User savedUser = userService.createUser(createUserDTO);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getUserName()).isEqualTo(user.getUserName());
        Assertions.assertThat(savedUser.getUserPassword()).isEqualTo(user.getUserPassword());
        Assertions.assertThat(savedUser.getUserEmail()).isEqualTo(user.getUserEmail());

        //verify Repository Interaction
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());

        //Assertions on the captured Argument
        User capturedUser = userCaptor.getValue();
        Assertions.assertThat(capturedUser.getUserName()).isEqualTo(createUserDTO.getUserName());
        Assertions.assertThat(capturedUser.getUserPassword()).isEqualTo(createUserDTO.getUserPassword());
        Assertions.assertThat(capturedUser.getUserEmail()).isEqualTo(createUserDTO.getUserEmail());
    }

    @Test
    public void getAllUsers()
    {
        List<User> userList = new ArrayList<>();

        userList.add(new User(
                "marufhasan",
                "123456",
                "marufhasan212@gmail.com"
        ));

        userList.add(new User(
                "maruf",
                "123456",
                "marufhasan@gmail.com"
        ));

        when(userRepository.findAll()).thenReturn(userList);

        List<User> responseUserList = userService.getAllUsers();
        Assertions.assertThat(responseUserList.size()).isEqualTo(2);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void getUser_givenUserName()
    {
        String userName = "marufhasan";
        User user = new User(
                "marufhasan",
                "123456",
                "marufhasan212@gmail.com"
        );
        when(userRepository.findByUserName(userName)).thenReturn(Optional.of(user));

        User responseUser = userService.getUserByUserName(userName);
        Assertions.assertThat(responseUser.getUserName()).isEqualTo(user.getUserName());
        Assertions.assertThat(responseUser.getUserPassword()).isEqualTo(user.getUserPassword());
        Assertions.assertThat(responseUser.getUserEmail()).isEqualTo(user.getUserEmail());
        verify(userRepository, times(1)).findByUserName(userName);
    }

    @Test
    public void updateUser_given_updateUser()
    {
        User user = new User("maruf","123458","marufhasan212@gmail.com");
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User updatedUser = new User(
            "maruf",
            "123456",
            "marufhasan@gmail.com"
        );

        updatedUser.setId(1L);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(updatedUser);


        User responseUser = userService.updateUser(updatedUser);

        //To capture the User object interacting with the repository
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository,times(1)).findById(1L);

        verify(userRepository, times(1)).save(userCaptor.capture());

        Assertions.assertThat(responseUser).isNotNull();
        Assertions.assertThat(responseUser.getUserEmail()).isEqualTo(updatedUser.getUserEmail());
        Assertions.assertThat(responseUser.getUserName()).isEqualTo(updatedUser.getUserName());
        Assertions.assertThat(responseUser.getUserPassword()).isEqualTo(updatedUser.getUserPassword());
    }

    @Test
    void deleteUser_givenUserId()
    {
        User user = new User("maruf","123456","marufhasan212@gmail.com");
        user.setId(1L);

        userService.deleteUser(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
    }
}
