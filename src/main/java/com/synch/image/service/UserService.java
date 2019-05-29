package com.synch.image.service;

import org.springframework.stereotype.Component;

import com.synch.image.entity.UserEntity;
import com.synch.image.pojo.User;

@Component
public interface UserService {

	public long registerUser(User user) throws Exception;
	public UserEntity authenticateUser(User user) throws Exception;
}
