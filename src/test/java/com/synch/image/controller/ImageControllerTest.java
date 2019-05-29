package com.synch.image.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.synch.image.entity.UserEntity;
import com.synch.image.exception.InvalidFileException;
import com.synch.image.exception.InvalidUserNameandPassword;
import com.synch.image.pojo.User;
import com.synch.image.response.ImageResponse;
import com.synch.image.service.ImageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageControllerTest {

	@InjectMocks
	private ImageController imageController;
	
	@Mock
	private ImageService imageService;
	
	@Mock
	MultipartFile multipartFile;
		
	@Test
	public void testUpload() throws Exception {
		User user = new User();
		user.setUserName("rathan");
		user.setPassword("test@123");
		
		UserEntity entity = new UserEntity();
		entity.setUserName(user.getUserName());
		entity.setPassword(user.getPassword());
		
		when(imageService.authenticateUser(user)).thenReturn(entity);
		when(imageService.uploadImages(multipartFile, entity)).thenReturn(new Long(1));
		ResponseEntity response = imageController.upload(multipartFile, user);
		
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		assertEquals(response.getBody(), new Long(1));
	}
	
	@Test(expected=InvalidUserNameandPassword.class)
	public void testUploadWithInvaliduserName() throws Exception {
		User user = new User();
		user.setUserName("");
		user.setPassword("test@123");
		
		UserEntity entity = new UserEntity();
		entity.setUserName(user.getUserName());
		entity.setPassword(user.getPassword());
		when(imageService.authenticateUser(user)).thenThrow(InvalidUserNameandPassword.class);
		when(imageService.uploadImages(multipartFile, entity)).thenReturn(new Long(1));
		ResponseEntity response = imageController.upload(multipartFile, user);
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		assertEquals(response.getBody(), new Long(1));
	}
	
	@Test(expected=InvalidFileException.class)
	public void testUploadWithInvalidFile() throws Exception {
		MultipartFile file = null;
		User user = new User();
		user.setUserName("rathan");
		user.setPassword("test@123");
		
		UserEntity entity = new UserEntity();
		entity.setUserName(user.getUserName());
		entity.setPassword(user.getPassword());
		ImageResponse imageResponse = new ImageResponse();
		when(imageService.uploadImages(multipartFile, entity)).thenReturn(new Long(1));
		ResponseEntity response = imageController.upload(file, user);
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);
		assertEquals(response.getBody(), new Long(1));
	}
	
	
	@Test
	public void testView() throws Exception {
		User user = new User();
		user.setUserName("123");
		user.setPassword("test@123");
		
		UserEntity entity = new UserEntity();
		entity.setUserName(user.getUserName());
		entity.setPassword(user.getPassword());
		ImageResponse imageResponse = new ImageResponse();
		when(imageService.authenticateUser(user)).thenReturn(entity);
		when(imageService.fetchUserImages(entity)).thenReturn(imageResponse);
		
		ResponseEntity<?> response = imageController.view(user);
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), imageResponse);
	}
	
	@Test
	public void testViewWithInvalidUserName() throws Exception {
		User user = new User();
		user.setUserName("");
		user.setPassword("test@123");
		
		UserEntity entity = new UserEntity();
		entity.setUserName(user.getUserName());
		entity.setPassword(user.getPassword());
		ImageResponse imageResponse = new ImageResponse();
		when(imageService.authenticateUser(user)).thenReturn(entity);
		when(imageService.fetchUserImages(entity)).thenReturn(imageResponse);
		ResponseEntity<?> response = imageController.view(user);
		assertNotNull(response);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), imageResponse);
	}
}
