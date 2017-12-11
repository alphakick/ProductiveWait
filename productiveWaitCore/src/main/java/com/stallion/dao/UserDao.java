package com.stallion.dao;

import java.sql.SQLException;

import com.stallion.domain.objects.User;

public interface UserDao {
	public User isUserExist(String userName) throws SQLException, ClassNotFoundException;
	public int addUser(User user) throws SQLException, ClassNotFoundException;
	public void removeUser(String userName) throws SQLException, ClassNotFoundException;
	public User isUserRegistered(String userName,String password) throws SQLException, ClassNotFoundException;
}
