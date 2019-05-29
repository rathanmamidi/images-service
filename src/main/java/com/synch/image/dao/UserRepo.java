package com.synch.image.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.synch.image.entity.UserEntity;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Integer> {

	public UserEntity findByUserName(String userName); 
	public UserEntity findByUserNameAndPassword(String userName, String password);
}
