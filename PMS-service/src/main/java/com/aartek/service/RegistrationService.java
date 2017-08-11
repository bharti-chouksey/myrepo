package com.aartek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aartek.model.Registration;
import com.aartek.repository.RegistrationRepository;

@Service
@SuppressWarnings("unused")
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	public Registration validate(Registration registration)
	{
		System.out.println("in the service method"+registration.getFname());
		registrationRepository.saveTheData(registration);
		return registration;
		
	}
	

}
