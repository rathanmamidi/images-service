package com.synch.image.response;

import java.util.List;

import com.synch.image.entity.ImageEntity;
import com.synch.image.entity.UserEntity;

public class ImageResponse {
	
	private UserEntity user;
	private List<ImageEntity> images;
	
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public List<ImageEntity> getImages() {
		return images;
	}
	public void setImages(List<ImageEntity> images) {
		this.images = images;
	}
}
