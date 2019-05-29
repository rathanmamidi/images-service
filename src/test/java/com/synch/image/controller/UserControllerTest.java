package com.synch.image.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.synch.image.entity.UserEntity;
import com.synch.image.exception.MandatoryParamtersMissing;
import com.synch.image.pojo.User;
import com.synch.image.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	@InjectMocks
	private UserController userController;
	
	@Mock
	private UserService userService;
	
	
	public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testRegisterUser() throws Exception {
		User user = new User();
		user.setUserName("rathan");
		user.setPassword("test@123");
		user.setFirstName("TestFirstName");
		user.setLastName("TestLastName");
		user.setEmailId("email@email.com");
		user.setGender("M");
		
		Long primaryKey = new Long(1);
		when(userService.registerUser(user)).thenReturn(primaryKey);
		ResponseEntity<?> response = userController.registerUser(user);
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		assertEquals(response.getBody(), new Long(1));
	}
	
	@Test(expected=MandatoryParamtersMissing.class)
	public void testRegisterUserWithInvalidFirstName() throws Exception {
		User user = new User();
		user.setUserName("rathan");
		user.setPassword("test@123");
		user.setLastName("TestLastName");
		user.setEmailId("email@email.com");
		user.setGender("M");
		Long primaryKey = new Long(1);
		when(userService.registerUser(user)).thenReturn(primaryKey);
		ResponseEntity<?> response = userController.registerUser(user);
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		assertEquals(response.getBody(), new Long(1));
	}
	
	@Test
	public void testFetchUser() throws Exception {
		User user = new User();
		user.setUserName("rathan");
		user.setPassword("test@123");
		
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName("rathan");
		userEntity.setPassword("test@123");
		userEntity.setFirstName("TestFirstName");
		userEntity.setLastname("TestLastName");
		userEntity.setEmailId("email@email.com");
		userEntity.setGender("M");
		
		when(userService.authenticateUser(user)).thenReturn(userEntity);
		ResponseEntity<UserEntity> response = (ResponseEntity<UserEntity>) userController.fetchUser(user);
		
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody().getUserName(), "rathan");
		
	}
	
	@Test(expected=MandatoryParamtersMissing.class)
	public void testFetchUserWithEmptyUserName() throws Exception {
		User user = new User();
		user.setUserName("");
		user.setPassword("test@123");
		
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName("rathan");
		userEntity.setPassword("test@123");
		userEntity.setFirstName("TestFirstName");
		userEntity.setLastname("TestLastName");
		userEntity.setEmailId("email@email.com");
		userEntity.setGender("M");
		
		when(userService.authenticateUser(user)).thenReturn(userEntity);
		ResponseEntity<UserEntity> response = (ResponseEntity<UserEntity>) userController.fetchUser(user);
		
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody().getUserName(), "rathan");
		
	}
}
