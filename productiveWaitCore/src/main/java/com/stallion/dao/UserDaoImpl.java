package com.stallion.dao;


import com.stallion.domain.objects.User;

import java.io.FileInputStream;
import java.io.IOException;
import 	java.sql.*;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;



public class UserDaoImpl implements UserDao{
	private static Properties  props = null;
	final static Logger logger = Logger.getLogger(UserDaoImpl.class);
	
	static {
		try {
		props = new Properties();
		Class.forName("com.mysql.jdbc.Driver");
		FileInputStream in = new FileInputStream("/Users/sumeetkhurana/git/LocalProductiveWaitRepository/productiveWaitCore/src/main/resources/db.properties");
		props.load(in);
		in.close();
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public User isUserExist(String userName) throws SQLException, ClassNotFoundException {
		User user = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement("select * FROM user WHERE user_name = ?");
			preparedStmt.setString(1,userName);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				System.out.println(user.getUserId());
			}
		}
		finally {
			cleanUpResources(conn, st, rs);
		}
		return user;
	}
	

	public int addUser(User user) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement st = null;
		int key = -1;
		
		try {
			conn = getConnection();
			String query = "insert into user(user_name,first_name,last_name,password,email)"
			        + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, user.getUserName());
            preparedStmt.setString(2, user.getFirstName());
            preparedStmt.setString(3, user.getLastName());
            preparedStmt.setString(4, user.getPassword());
            preparedStmt.setString(5, user.getEmail());
            preparedStmt.executeUpdate();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
                logger.debug("New user key: " + key);
            }
            return key;
		}finally {
			cleanUpResources(conn, st);
		}
	}
	
	public void removeUser(String userName) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement("DELETE FROM user WHERE user_name = ?");
			preparedStmt.setString(1,userName);
			preparedStmt.executeUpdate();
		}finally {
			cleanUpResources(conn, st);
		}
	}
	
	private void cleanUpResources(Connection conn,Statement st,ResultSet rs) {
	    	DbUtils.closeQuietly(rs);
	    	DbUtils.closeQuietly(st);
	    	DbUtils.closeQuietly(conn);
	}
	
	private void cleanUpResources(Connection conn,Statement st) {
	    	DbUtils.closeQuietly(st);
	    	DbUtils.closeQuietly(conn);
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
		return conn;
	}


	public User isUserRegistered(String userName, String password) throws SQLException, ClassNotFoundException {
		User user = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement("select * FROM user WHERE user_name = ? and password = ?");
			preparedStmt.setString(1,userName);
			preparedStmt.setString(2,password);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setPassword(rs.getString("password"));
				user.setCreate_dt(rs.getTimestamp("create_dt"));
			}
		} 
		finally {
			cleanUpResources(conn, st, rs);
		}
		return user;
	}

}
