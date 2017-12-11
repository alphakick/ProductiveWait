package com.stallion.services;

import java.sql.SQLException;

import com.stallion.dao.AccountDao;
import com.stallion.dao.AccountDaoImpl;
import com.stallion.domain.objects.Account;

public class AccountService {
     public Account getAccountDetails(int accountId) throws SQLException, ClassNotFoundException {
    	    AccountDao accountDao = new AccountDaoImpl();
    	    Account account = accountDao.getAccountDetails(accountId);
		return account;
     }
     
     public Account isAccountExist(int userId) throws SQLException,ClassNotFoundException{
    	    AccountDao accountDao = new AccountDaoImpl();
 	    Account account = accountDao.isAccountExist(userId);
		return account;
     }
 	public int addAccount(Account account) throws SQLException,ClassNotFoundException{
 		 AccountDao accountDao = new AccountDaoImpl();
 		 return accountDao.addAccount(account);
 	}
 	public void removeAccount(int userId) throws SQLException,ClassNotFoundException{
 		 AccountDao accountDao = new AccountDaoImpl();
 		 accountDao.removeAccount(userId);
 	}
}
