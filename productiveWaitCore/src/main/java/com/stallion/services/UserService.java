package com.stallion.services;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import com.stallion.dao.UserDao;
import com.stallion.dao.UserDaoImpl;
import com.stallion.domain.objects.User;

public class UserService {
	
	public boolean isUserAuthorized(User user,String password) {
		boolean isAuthorized = false;
		if(user!=null && StringUtils.isNotBlank(user.getPassword()) && user.getPassword().equals(password)) {
             isAuthorized = true;			
		}
		return isAuthorized;
	}
	
	public User isUserRegistered(String userName,String password) throws SQLException, ClassNotFoundException {
		User user  = null;
		if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
			UserDao userDao = new UserDaoImpl();
			user = userDao.isUserRegistered(userName, password);		
		}
		return user;
	}
	
	public int addUser(User user) throws SQLException, ClassNotFoundException {
		UserDao userDao = new UserDaoImpl();
		return userDao.addUser(user);
	}

}
