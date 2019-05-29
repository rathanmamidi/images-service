package com.synch.image.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synch.image.entity.UserEntity;
import com.synch.image.pojo.User;
import com.synch.image.service.UserService;
import com.synch.image.validation.UserValidations;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/testapp")
	public ResponseEntity<?> testApp() {
		return new ResponseEntity<>("Server is up", HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody User user) throws Exception {
		UserValidations.validateRegisterUser(user);
		Long userId = userService.registerUser(user);
		return new ResponseEntity<>(userId, HttpStatus.CREATED);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> fetchUser(@RequestBody User user) throws Exception {
		UserValidations.authUserValidations(user);
		UserEntity userEntity = userService.authenticateUser(user);
		return new ResponseEntity<>(userEntity, HttpStatus.OK);
	}	
}
