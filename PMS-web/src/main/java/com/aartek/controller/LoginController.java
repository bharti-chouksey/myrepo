package com.aartek.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aartek.model.User;
import com.aartek.service.LoginService;
@Controller
public class LoginController {
	@Autowired
	private LoginService loginservice;
	@RequestMapping("/login")
	public String showLogin(Map<String, Object> map) 
	{
		System.out.println("Intialized LoginController....!!!");
		map.put("User", new User());
		return "login";
	}
	@RequestMapping(value="/showLogin", method = RequestMethod.POST)
	public String checkLogin(@ModelAttribute("User") User user , Map<String, Object> map)
	{
		System.out.println("inside check controller");
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		map.put("User", new User());
		loginservice.validate(user);
		return "login";
		
	}

}
