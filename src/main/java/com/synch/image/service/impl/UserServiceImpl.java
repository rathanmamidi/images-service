package com.synch.image.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.synch.image.dao.UserRepo;
import com.synch.image.entity.UserEntity;
import com.synch.image.exception.InvalidUserNameandPassword;
import com.synch.image.pojo.User;
import com.synch.image.service.UserService;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public long registerUser(User user) throws Exception {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(user.getUserName());
		userEntity.setPassword(user.getPassword());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastname(user.getLastName());
		userEntity.setEmailId(user.getEmailId());
		userEntity.setGender(user.getGender());
		
		try {
			userEntity = userRepo.save(userEntity);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("username already exist, please choose different username");
		} catch (Exception e) {
			throw new Exception("Something went worng please try again");
		}
		return userEntity.getId();
	}

	public UserEntity fetchUserByUserName(String userName) {
		UserEntity user = userRepo.findByUserName(userName);
		return user;
	}

	@Override
	public UserEntity authenticateUser(User user) throws Exception {
		UserEntity userEntity = userRepo.findByUserNameAndPassword(user.getUserName(), user.getPassword());
		if (userEntity == null)  throw new InvalidUserNameandPassword("invalid username and password");
		return userEntity;
	}
}
