package com.stallion.dao;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.stallion.domain.objects.Account;
import com.stallion.domain.objects.User;


public class AccountDaoImplTest {
	
	@BeforeClass
	public static void testLoad() {
		AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
	}
	
	@Test
	public void testAddAccount() throws SQLException, ClassNotFoundException {
		AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
		Account account = new Account();
		account.setUserId(1);
		account.setFirstName("Sumeet");
		account.setLastName("Khurana");
		account.setCcNumber("1234567844445555");
		account.setCcExpiry("0412");
		account.setCcType("M");
		account.setCvv("234");
		account.setAddressLine1("2353 Berkshire Lane");
		account.setAddressLine2("");
		account.setAddressLine3("");
		account.setCity("North Brunswick");
		account.setCountry("USA");
		account.setZip("08902");
		accountDaoImpl.addAccount(account);
	}
	
	@Test
	public void testRemoveAccount() throws SQLException, ClassNotFoundException {
		AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
		accountDaoImpl.removeAccount(1);
	}
	
	@Test
	public void testIsAccountExist() throws SQLException, ClassNotFoundException {
		AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
		accountDaoImpl.isAccountExist(1);
	}


}
