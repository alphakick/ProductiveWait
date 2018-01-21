package com.stallion.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;

import com.stallion.domain.objects.Visitor;



public class VisitorDaoImpl implements VisitorDao{
	private static Properties  props = null;
	
	static {
		try {
		props = new Properties();
		FileInputStream in = new FileInputStream("/Users/sumeetkhurana/git/LocalProductiveWaitRepository/productiveWaitCore/src/main/resources/db.properties");
		props.load(in);
		in.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public Visitor getVisitorByPhoneNumber(String phoneNumber, int account_id) throws SQLException, ClassNotFoundException {
		Visitor visitor = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement("select * FROM visitor WHERE account_id = ? and phone_number = ?");
			preparedStmt.setInt(1,account_id);
			preparedStmt.setString(2,phoneNumber);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				visitor = new Visitor();
				visitor.setPhoneNumber(rs.getString("phone_number"));
				visitor.setAccountId(rs.getInt("account_id"));
				visitor.setFirstName(rs.getString("first_name"));
				visitor.setLastName(rs.getString("last_name"));
				visitor.setAddressLine1(rs.getString("address_line1"));
				visitor.setAddressLine2(rs.getString("address_line2"));
				visitor.setAddressLine3(rs.getString("address_line3"));
				visitor.setCity(rs.getString("city"));
				visitor.setState(rs.getString("state"));
				visitor.setCountry(rs.getString("country"));
				visitor.setZip(rs.getString("zip"));
				visitor.setEmail(rs.getString("email"));
				System.out.println("Fetched visitor" + visitor.toString());
			}
		}
		finally {
			cleanUpResources(conn, st, rs);
		}
		return visitor;
	}
	

	public void addVisitor(Visitor visitor) throws SQLException, ClassNotFoundException{
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = getConnection();
			String query = "insert into visitor(phone_number,account_id,first_name,last_name,address_line1,address_line2,address_line3,city,state,country,zip,email)"
			        + " values (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, visitor.getPhoneNumber());
            preparedStmt.setInt(2, visitor.getAccountId());
            preparedStmt.setString(3, visitor.getFirstName());
            preparedStmt.setString(4, visitor.getLastName());
            preparedStmt.setString(5, visitor.getAddressLine1());
            preparedStmt.setString(6, visitor.getAddressLine2());
            preparedStmt.setString(7, visitor.getAddressLine3());
            preparedStmt.setString(8, visitor.getCity());
            preparedStmt.setString(9, visitor.getState());
            preparedStmt.setString(10, visitor.getCountry());
            preparedStmt.setString(11, visitor.getZip());
            preparedStmt.setString(12, visitor.getEmail());
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

	public List<Visitor> getAllVisitorsPerAccount(int account_id) throws SQLException, ClassNotFoundException {
		List<Visitor> visitorList = new ArrayList<Visitor>();
		Visitor visitor = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement("select * FROM visitor WHERE account_id = ?");
			preparedStmt.setInt(1,account_id);
			rs = preparedStmt.executeQuery();
			while(rs.next()) {
				visitor = new Visitor();
				visitor.setPhoneNumber(rs.getString("phone_number"));
				visitor.setAccountId(rs.getInt("account_id"));
				visitor.setFirstName(rs.getString("first_name"));
				visitor.setLastName(rs.getString("last_name"));
				visitor.setAddressLine1(rs.getString("address_line1"));
				visitor.setAddressLine2(rs.getString("address_line2"));
				visitor.setAddressLine3(rs.getString("address_line3"));
				visitor.setCity(rs.getString("city"));
				visitor.setState(rs.getString("state"));
				visitor.setCountry(rs.getString("country"));
				visitor.setZip(rs.getString("zip"));
				visitor.setEmail(rs.getString("email"));
				System.out.println("Fetched visitor" + visitor.toString());
				visitorList.add(visitor);
			}
		}
		finally {
			cleanUpResources(conn, st, rs);
		}
		return visitorList;	
	}

}
