package com.synch.image.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.synch.image.dao.ImageRepo;
import com.synch.image.entity.ImageEntity;
import com.synch.image.entity.UserEntity;
import com.synch.image.exception.InvalidUserNameandPassword;
import com.synch.image.exception.MandatoryParamtersMissing;
import com.synch.image.exception.NoImagesFoundException;
import com.synch.image.pojo.User;
import com.synch.image.response.ImageResponse;
import com.synch.image.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ImageRepo imageRepo;
	
	String url = "http://localhost:9000/user/authenticate";
	
	@Override
	public UserEntity authenticateUser(User user) throws Exception{
		ResponseEntity<UserEntity> response = null;
		try {
			response = restTemplate.postForEntity(url, user, UserEntity.class);
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			if (e.getStatusCode() == HttpStatus.NOT_FOUND) throw new InvalidUserNameandPassword("invalid username and password");
			if (e.getStatusCode() == HttpStatus.BAD_REQUEST) throw new MandatoryParamtersMissing("mandatory paramters are missing");
		}
		return response.getBody();
	}

	@Override
	public Long uploadImages(MultipartFile file, UserEntity user) throws Exception {
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setUserId(user.getId());
		imageEntity.setFileName(file.getOriginalFilename());
		imageEntity.setData(file.getBytes());
		ImageEntity imageEntityRep = imageRepo.save(imageEntity);
		return imageEntityRep.getId();
	}

	@Override
	public ImageResponse fetchUserImages(UserEntity userEntity) throws Exception {
		List<ImageEntity> images = imageRepo.findByUserId(userEntity.getId());
		if (images == null) throw new NoImagesFoundException("No images found for given username");
		ImageResponse response = new ImageResponse();
		response.setImages(images);
		response.setUser(userEntity);
		return response;
	}

	@Override
	public Long deleteImage(User user, UserEntity userEntity) throws Exception {
		ImageEntity imageEntity = new ImageEntity();
		imageEntity.setId(user.getImageId());
		imageEntity.setUserId(userEntity.getId());
		imageRepo.delete(imageEntity);
		return user.getImageId();
	}
}
