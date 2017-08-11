package com.aartek.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.aartek.model.User;
@SuppressWarnings("unchecked")
@Repository
public class LoginRepository {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/*public User val(User user)
	{
		System.out.println("LoginRepository \t"+user.getEmail()+"\t"+user.getPassword());
		return user;
		
	}*/
	public List<User> adminSignIn(String email, String password) {
		if (email != null && password != null) {
			List<User> login = hibernateTemplate.find("from User u where u.email=? and u.password=?" ,email,password);
			return login;
		} else {
			return null;
		}
	}

}
