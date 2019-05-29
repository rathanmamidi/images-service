package com.synch.image.validation;

import org.springframework.web.multipart.MultipartFile;

import com.synch.image.exception.InvalidFileException;

public class ImageValidation {

	public static void fileValidation(MultipartFile file) {
		if (file == null) {
			throw new InvalidFileException("Invalid File");
		}
	}
}
