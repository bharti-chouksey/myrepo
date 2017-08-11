package com.aartek.validator;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.aartek.model.Registration;
import com.aartek.repository.RegistrationRepository;

@SuppressWarnings("unused")
@Component
public class RegistrationValidator implements Validator {

	@Autowired
	RegistrationRepository registrationRepository;

	public boolean supports(Class<?> clazz) {
		return Registration.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {

		Registration registration = (Registration) target;
		ValidationUtils.rejectIfEmpty(errors, "fname", "error.firstName.empty");
		ValidationUtils.rejectIfEmpty(errors, "lname", "error.lastName.empty");
		ValidationUtils.rejectIfEmpty(errors, "email", "error.emailId.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "error.password.empty");
		if (registration.getEmail() != null && registration.getEmail().trim().length() > 0) {
			if (registration.getEmail().contains("@") != true) {
				errors.rejectValue("email", "error.email.first.rule");
			} else if (registration.getEmail().contains(".com") != true
					&& registration.getEmail().contains(".net") != true
					&& registration.getEmail().contains(".co.in") != true) {
				errors.rejectValue("email", "error.email.second.rule");
			}
		}
		if (registration.getFname() != null && registration.getFname().trim().length() > 0) {
			if (registration.getFname().length() == 50) {
				errors.rejectValue("fname", "error.fname.limit");
			}
		}
		if (registration.getLname() != null && registration.getLname().trim().length() > 0) {
			if (registration.getLname().length() == 50) {
				errors.rejectValue("lname", "error.lname.limit");
			}
		}
		if (registration.getPassword() != null && registration.getPassword().trim().length() > 0) {
			int len = registration.getPassword().length();
			if (len < 8 ) {
				errors.rejectValue("password", "error.password.length");
			}

			else if (registration.getPassword().contains("@") != true
					&& registration.getPassword().contains("#") != true
					&& registration.getPassword().contains("$") != true) {
				errors.rejectValue("password", "error.password.special");
			}
		}

		String email = registration.getEmail();
		boolean b = registrationRepository.validateEmail(email).isEmpty();
		if (b == false) {
			errors.rejectValue("email", "error.email.exist");
		}

	}

}
