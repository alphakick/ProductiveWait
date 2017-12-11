package com.stallion.dao;

import java.sql.SQLException;

import com.stallion.domain.objects.Account;
import com.stallion.domain.objects.Plan;


public interface PlanDao {
	public void addPlan(Plan plan) throws SQLException,ClassNotFoundException;
	public void removePlan(int accountId) throws SQLException,ClassNotFoundException;
	public void updatePlanActivity(int accountId,int userID, int unsentSMS,int sentSMS) throws SQLException,ClassNotFoundException;
}
