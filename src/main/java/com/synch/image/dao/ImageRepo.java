package com.synch.image.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.synch.image.entity.ImageEntity;

@Repository
public interface ImageRepo extends CrudRepository<ImageEntity, Long> {
	public List<ImageEntity> findByUserId(Long userId);
}
