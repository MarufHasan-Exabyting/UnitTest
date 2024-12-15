package com.marufhasan.UnitTest;

import com.marufhasan.UnitTest.model.User;
import com.marufhasan.UnitTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnitTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnitTestApplication.class, args);
	}
}
