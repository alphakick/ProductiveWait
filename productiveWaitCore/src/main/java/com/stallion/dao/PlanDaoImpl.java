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

import com.stallion.domain.objects.Account;
import com.stallion.domain.objects.Plan;



public class PlanDaoImpl implements PlanDao{
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
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
		return conn;
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


	public void addPlan(Plan plan) throws SQLException,ClassNotFoundException {
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = getConnection();
			String query = "insert into plan(plan_name,plan_description,plan_type,account_id,sms_included,rollover_sms,per_sms_charge,recurring_charge,billing_frequency)"
			        + " values (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?,?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, plan.getPlanName());
            preparedStmt.setString(2, plan.getPlanDescription());
            preparedStmt.setString(3, plan.getPlanType());
            preparedStmt.setInt(4, plan.getAccountId());
            preparedStmt.setInt(5, plan.getSmsIncluded());
            preparedStmt.setInt(6, plan.getRollOverSms());
            preparedStmt.setFloat(7, plan.getPerSMSCharge());
            preparedStmt.setFloat(8, plan.getRecurringCharge());
            preparedStmt.setInt(9, plan.getBillingFrequency());
			preparedStmt.executeUpdate();
		}finally {
			cleanUpResources(conn, st);
		}
		
	}


	public void removePlan(int accountId) throws SQLException,ClassNotFoundException {
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = getConnection();
			PreparedStatement preparedStmt = conn.prepareStatement("DELETE FROM plan WHERE account_id = ?");
			preparedStmt.setInt(1,accountId);
			preparedStmt.executeUpdate();
		}finally {
			cleanUpResources(conn, st);
		}
		
	}


	public void updatePlanActivity(int accountId,int userID, int unsentSMS,int sentSMS) throws SQLException,ClassNotFoundException {
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = getConnection();
			String query = "insert into plan(account_id,user_id,net_unsent_sms,sms_sent)"
			        + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, accountId);
            preparedStmt.setInt(2, userID);
            preparedStmt.setInt(3, unsentSMS);
            preparedStmt.setInt(4, sentSMS);
            
			preparedStmt.executeUpdate();
		}finally {
			cleanUpResources(conn, st);
		}
		
	}

}
