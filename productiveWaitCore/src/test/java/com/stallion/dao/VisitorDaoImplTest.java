package com.stallion.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.stallion.domain.objects.Account;
import com.stallion.domain.objects.User;
import com.stallion.domain.objects.Visitor;


public class VisitorDaoImplTest {
	
	@BeforeClass
	public static void testLoad() {
		VisitorDaoImpl visitorDaoImpl = new VisitorDaoImpl();
	}
	
	@Test
	public void testAddVisitor() throws SQLException, ClassNotFoundException {
		VisitorDaoImpl visitorDaoImpl = new VisitorDaoImpl();
		Visitor visitor = new Visitor();
		visitor.setAccountId(1);
		visitor.setFirstName("visitor_FN");
		visitor.setLastName("visitor_LN");
		visitor.setAddressLine1("visitor_adl_1");
		visitor.setAddressLine2("visitor_adl_2");
		visitor.setAddressLine3("visitor_adl_3");
		visitor.setCity("North Brunswick");
		visitor.setState("NJ");
		visitor.setCountry("USA");
		visitor.setZip("08902");
		visitor.setPhoneNumber("1234567890");
		visitor.setEmail("test123@yahoo.com");
		visitorDaoImpl.addVisitor(visitor);
	}
	
	@Test
	public void testGetVisitorByPhoneNumber() throws SQLException, ClassNotFoundException {
		VisitorDaoImpl visitorDaoImpl = new VisitorDaoImpl();
		Visitor visitor = visitorDaoImpl.getVisitorByPhoneNumber("1234567890", 1);
		System.out.println("visitor: " + visitor.toString());
	}
	
	@Test
	public void testGetAllVisitorsPerAccount() throws SQLException, ClassNotFoundException {
		VisitorDaoImpl visitorDaoImpl = new VisitorDaoImpl();
		List<Visitor> visitorList = visitorDaoImpl.getAllVisitorsPerAccount(1);
		for(Visitor visitor:visitorList) {
			System.out.println(visitor.toString());
		}
	}


}
