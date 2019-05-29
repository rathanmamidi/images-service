package com.synch.image.validation;

import com.synch.image.exception.MandatoryParamtersMissing;
import com.synch.image.pojo.User;

public class UserValidations {
	
	public static void validateRegisterUser(User user) {
		if (user == null || user.getUserName() == null || user.getUserName().trim().length() <=0 
				|| user.getPassword() == null || user.getPassword().trim().length()<=0
				|| user.getFirstName() == null || user.getFirstName().trim().length()<=0
				|| user.getLastName() == null || user.getLastName().trim().length()<=0
				|| user.getEmailId() == null || user.getEmailId().trim().length()<=0
				|| user.getGender() == null || user.getGender().trim().length()<=0) {
			throw new MandatoryParamtersMissing("Mandatory paramters are missing");
		}
	}
	
	public static void authUserValidations(User user) {
		if (user == null || user.getUserName() == null || user.getUserName().trim().length() <=0 
				|| user.getPassword() == null || user.getPassword().trim().length()<=0) {
			throw new MandatoryParamtersMissing("Mandatory paramters are missing");
		}
	}
}
