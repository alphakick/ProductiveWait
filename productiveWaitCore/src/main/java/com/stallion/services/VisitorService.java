package com.stallion.services;

import java.sql.SQLException;
import java.util.List;

import com.stallion.dao.VisitorDao;
import com.stallion.dao.VisitorDaoImpl;
import com.stallion.domain.objects.Visitor;

public class VisitorService {
	
	public Visitor getVisitorByPhone(String phoneNumber,int accoundId) throws SQLException, ClassNotFoundException {
		Visitor visitor = null;
		VisitorDao visitorDao = new VisitorDaoImpl();
		visitor = visitorDao.getVisitorByPhoneNumber(phoneNumber, accoundId);
		return visitor;
	}

	public List<Visitor> getVisitorByAccount(int accoundId) throws SQLException, ClassNotFoundException {
		List<Visitor> visitorList = null;
		VisitorDao visitorDao = new VisitorDaoImpl();
		visitorList = visitorDao.getAllVisitorsPerAccount(accoundId);
		return visitorList;
	}
	
	public void addVisitor(Visitor visitor) throws SQLException, ClassNotFoundException {
		VisitorDao visitorDao = new VisitorDaoImpl();
		visitorDao.addVisitor(visitor);
	}
}
