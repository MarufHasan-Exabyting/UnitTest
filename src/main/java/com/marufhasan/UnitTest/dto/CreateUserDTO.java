package com.marufhasan.UnitTest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {

    @NotEmpty(message = "UserName is required")
    @Pattern(regexp="^[A-Za-z0-9_]*$", message = "Invalid Input")
    private String userName;

    @NotEmpty(message = "password is required")
    @Size(min = 6,max = 20,message = "Length of password should be between 6-20")
    private String userPassword;

    @Email
    @NotEmpty(message = "User Email can not be empty")
    private String userEmail;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String userName, String userPassword, String userEmail) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
