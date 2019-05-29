package com.synch.image.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.synch.image.entity.UserEntity;
import com.synch.image.pojo.User;
import com.synch.image.response.ImageResponse;

@Component
public interface ImageService {
	
	public UserEntity authenticateUser(User user) throws Exception;
	public Long uploadImages(MultipartFile file, UserEntity user) throws Exception;
	public ImageResponse fetchUserImages(UserEntity userEntity) throws Exception;
	public Long deleteImage(User user, UserEntity userEntity) throws Exception;
}
