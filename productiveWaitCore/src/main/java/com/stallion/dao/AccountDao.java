package com.stallion.dao;

import java.sql.SQLException;

import com.stallion.domain.objects.Account;


public interface AccountDao {
	public Account isAccountExist(int userId) throws SQLException,ClassNotFoundException;
	public int addAccount(Account account) throws SQLException,ClassNotFoundException;
	public void removeAccount(int userId) throws SQLException,ClassNotFoundException;
	public Account getAccountDetails(int accoundId) throws SQLException,ClassNotFoundException;
}
