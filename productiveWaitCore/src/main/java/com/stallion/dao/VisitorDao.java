package com.stallion.dao;

import java.sql.SQLException;
import java.util.List;

import com.stallion.domain.objects.Visitor;


public interface VisitorDao {	
	public void addVisitor(Visitor visitor) throws SQLException, ClassNotFoundException;
	public Visitor getVisitorByPhoneNumber(String phoneNumber, int account_id) throws SQLException, ClassNotFoundException;
	public List<Visitor> getAllVisitorsPerAccount(int account_id) throws SQLException, ClassNotFoundException;
}
