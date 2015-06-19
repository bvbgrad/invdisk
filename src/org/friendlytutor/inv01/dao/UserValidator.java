package org.friendlytutor.inv01.dao;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors arg1) {
		System.out.println("UserValidator");
	}

}
