package com.stallion.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;


import com.stallion.domain.objects.Account;
import com.stallion.domain.objects.Plan;

public class AccountDaoImpl implements AccountDao{
	private static Properties  props = null;
	final static Logger logger = Logger.getLogger(AccountDaoImpl.class);
	
	static {
		try {
		props = new Properties();
		FileInputStream in = new FileInputStream("/Users/sumeetkhurana/projects/src/main/resources/db.properties");
		props.load(in);
		in.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public Account isAccountExist(int userId) throws SQLException, ClassNotFoundException {
		logger.debug("In isAccountExist params userId :" + userId);
		Account account = null;
		Connection conn = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			preparedStmt = conn.prepareStatement("select * FROM account WHERE user_id = ?");
			preparedStmt.setInt(1,userId);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				account = new Account();
				account.setAccountId(rs.getInt("account_id"));
				account.setUserId(rs.getInt("user_id"));
				account.setFirstName(rs.getString("first_name"));
				account.setLastName(rs.getString("last_name"));
				//TODO: Add middlename to the domian object
				account.setCcNumber(rs.getString("cc_number"));
				account.setCcExpiry(rs.getString("cc_expiry"));
				account.setCcType(rs.getString("cc_type"));
				account.setCvv(rs.getString("cvv"));
				account.setAddressLine1(rs.getString("address_line1"));
				account.setAddressLine2(rs.getString("address_line2"));
				account.setAddressLine3(rs.getString("address_line3"));
				account.setCity(rs.getString("city"));
				account.setState(rs.getString("state"));
				account.setCountry(rs.getString("country"));
				account.setZip(rs.getString("zip"));
				account.setCreate_dt(rs.getTimestamp("create_dt"));
				logger.debug(account.toString());
			}
		}
		finally {
			cleanUpResources(conn, preparedStmt, rs);
		}
		return account;
	}
	

	public int addAccount(Account account) throws SQLException, ClassNotFoundException {
		logger.debug("In addAccount params Account :" + account.toString());
		Connection conn = null;
		PreparedStatement preparedStmt  = null;
		int key = -1;
		
		try {
			conn = getConnection();
			String query = "insert into account(user_id,first_name,last_name,cc_number,cc_expiry,cc_type,address_line1,address_line2,address_line3,city,state,country,zip)"
			        + " values (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?,?,?)";
		    preparedStmt = conn.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStmt.setInt(1, account.getUserId());
            preparedStmt.setString(2, account.getFirstName());
            preparedStmt.setString(3, account.getLastName());
            preparedStmt.setString(4, account.getCcNumber());
            preparedStmt.setString(5, account.getCcExpiry());
            preparedStmt.setString(6, account.getCcType());
            preparedStmt.setString(7, account.getAddressLine1());
            preparedStmt.setString(8, account.getAddressLine2());
            preparedStmt.setString(9, account.getAddressLine3());
            preparedStmt.setString(10, account.getCity());
            preparedStmt.setString(11, account.getState());
            preparedStmt.setString(12, account.getCountry());
            preparedStmt.setString(13, account.getZip());
			preparedStmt.executeUpdate();
			ResultSet rs = preparedStmt.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
                logger.debug("New account key : " + key);
            }
            return key;
		}finally {
			cleanUpResources(conn, preparedStmt);
		}
	}
	
	public void removeAccount(int userId) throws SQLException, ClassNotFoundException {
		logger.debug("In removeAccount params userId :" + userId);
		Connection conn = null;
		PreparedStatement preparedStmt = null;
		
		try {
			conn = getConnection();
			preparedStmt = conn.prepareStatement("DELETE FROM account WHERE user_id = ?");
			preparedStmt.setInt(1,userId);
			preparedStmt.executeUpdate();
		}finally {
			cleanUpResources(conn, preparedStmt);
		}
	}
	
	public Account getAccountDetails(int accoundId) throws SQLException, ClassNotFoundException{
		logger.debug("In getAccountDetails params accoundId :" + accoundId);
		Account account = null;
		Connection conn = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			preparedStmt = conn.prepareStatement("select * FROM account WHERE account_id = ?");
			preparedStmt.setInt(1,accoundId);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				account = new Account();
				account.setAccountId(rs.getInt("account_id"));
				account.setUserId(rs.getInt("user_id"));
				account.setFirstName(rs.getString("first_name"));
				account.setLastName(rs.getString("last_name"));
				//TODO: Add middlename to the domian object
				account.setCcNumber(rs.getString("cc_number"));
				account.setCcExpiry(rs.getString("cc_expiry"));
				account.setCcType(rs.getString("cc_type"));
				account.setCvv(rs.getString("cvv"));
				account.setAddressLine1(rs.getString("address_line1"));
				account.setAddressLine2(rs.getString("address_line2"));
				account.setAddressLine3(rs.getString("address_line3"));
				account.setCity(rs.getString("city"));
				account.setState(rs.getString("state"));
				account.setCountry(rs.getString("country"));
				account.setZip(rs.getString("zip"));
				account.setCreate_dt(rs.getTimestamp("create_dt"));
				logger.debug(account.toString());
			}
		}
		finally {
			cleanUpResources(conn, preparedStmt, rs);
		}
		return account;
	}
	
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
		return conn;
	}
	
	private void cleanUpResources(Connection conn,PreparedStatement st,ResultSet rs) throws SQLException {
//	    	DbUtils.closeQuietly(rs);
//	    	DbUtils.closeQuietly(st);
//	    	DbUtils.closeQuietly(conn);
		rs.close();
		st.close();
		conn.close();
	}
	
	private void cleanUpResources(Connection conn,PreparedStatement st) throws SQLException {
//	    	DbUtils.closeQuietly(st);
//	    	DbUtils.closeQuietly(conn);
		st.close();
		conn.close();
	}




}
