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
/*		Visitor visitor = new Visitor();
		visitor.setAccountId(1);
		visitor.setFirstName("Sumeet");
		visitor.setLastName("Khurana");
		visitor.setAddressLine1("2353 Berkshire Lane");
		visitor.setAddressLine2("");
		visitor.setAddressLine3("");
		visitor.setCity("North Brunswick");
		visitor.setState("NJ");
		visitor.setCountry("USA");
		visitor.setZip("08902");
		visitor.setPhoneNumber("9085876339");
		visitor.setEmail("khurana26@yahoo.com");*/
		
	/*	Visitor visitor = new Visitor();
		visitor.setAccountId(1);
		visitor.setFirstName("Kameshwari");
		visitor.setLastName("Bulusu");
		visitor.setAddressLine1("2353 Berkshire Lane");
		visitor.setAddressLine2("");
		visitor.setAddressLine3("");
		visitor.setCity("North Brunswick");
		visitor.setState("NJ");
		visitor.setCountry("USA");
		visitor.setZip("08902");
		visitor.setPhoneNumber("9085876338");
		visitor.setEmail("kams.bulusu@gmail.com");*/
		
		
		Visitor visitor = new Visitor();
		visitor.setAccountId(1);
		visitor.setFirstName("Yashvi");
		visitor.setLastName("Khurana");
		visitor.setAddressLine1("2353 Berkshire Lane");
		visitor.setAddressLine2("");
		visitor.setAddressLine3("");
		visitor.setCity("North Brunswick");
		visitor.setState("NJ");
		visitor.setCountry("USA");
		visitor.setZip("08902");
		visitor.setPhoneNumber("7326586109");
		visitor.setEmail("kams_bulusu@yahoo.com");
		
		
/*		Visitor visitor = new Visitor();
		visitor.setAccountId(1);
		visitor.setFirstName("Bluey");
		visitor.setLastName("Khurana");
		visitor.setAddressLine1("2353 Berkshire Lane");
		visitor.setAddressLine2("");
		visitor.setAddressLine3("");
		visitor.setCity("North Brunswick");
		visitor.setState("NJ");
		visitor.setCountry("USA");
		visitor.setZip("08902");
		visitor.setPhoneNumber("2016476660");
		visitor.setEmail("khurana26@gmail.com");*/
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
