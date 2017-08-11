package com.aartek.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aartek.model.Registration;
import com.aartek.service.RegistrationService;

@Controller
@SuppressWarnings("unused")
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;
	
	@RequestMapping("/registration")
	public String showRegistraion(Map<String,Object> map)
	{
		System.out.println("Inside RagistrationController");
		map.put("Registration", new Registration());
		return "registration";
	}
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String checkLogin(@ModelAttribute("Registration") Registration registration , Map<String, Object> map)
	{
		System.out.println("inside check controller");
		System.out.println(registration.getFname());
		map.put("Registration", new Registration());
		registrationService.validate(registration);
		return "registration";
		
	}

}
