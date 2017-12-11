package com.stallion.dao;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.stallion.domain.objects.User;


public class UserDaoImplTest {
	
	@BeforeClass
	public static void testLoad() {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
	}
	
	@Test
	public void testAddUser() throws SQLException, ClassNotFoundException {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		User user = new User();
		user.setUserName("khurana261");
		user.setEmail("khurana26@yahoo.com");
		user.setFirstName("Sumeet");
		user.setLastName("Khurana");
		user.setPassword("test123");
		userDaoImpl.addUser(user);
	}
	
	@Test
	public void testRemoveUser() throws SQLException, ClassNotFoundException {
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		userDaoImpl.removeUser("khurana26");
	}
	
	@Test
	public void testIsUserExist() throws SQLException, ClassNotFoundException{
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		userDaoImpl.isUserExist("khurana26");
	}


}
