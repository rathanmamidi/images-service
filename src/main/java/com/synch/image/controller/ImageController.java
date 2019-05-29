package com.synch.image.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synch.image.entity.UserEntity;
import com.synch.image.pojo.User;
import com.synch.image.response.ImageResponse;
import com.synch.image.service.ImageService;
import com.synch.image.validation.ImageValidation;

@RestController
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@GetMapping("/testapp")
	public ResponseEntity<?> testApp() {
		return new ResponseEntity<>("Server is up", HttpStatus.OK);
	}
	
	@PostMapping(value="/upload", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, User user) throws Exception {
		UserEntity userEntity = imageService.authenticateUser(user);
		ImageValidation.fileValidation(file);
		Long pkey = imageService.uploadImages(file, userEntity);
		return new ResponseEntity<>(pkey, HttpStatus.CREATED);
	}
	
	@PostMapping("/view")
	public ResponseEntity<?> view(@RequestBody User user) throws Exception {
		UserEntity userEntity = imageService.authenticateUser(user);
		ImageResponse imageResponse = imageService.fetchUserImages(userEntity);
		return new ResponseEntity<>(imageResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody User user) throws Exception {
		UserEntity userEntity = imageService.authenticateUser(user);
		imageService.deleteImage(user, userEntity);
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}
}
