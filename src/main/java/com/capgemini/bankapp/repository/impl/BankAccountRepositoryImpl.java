package com.capgemini.bankapp.repository.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.repository.BankAccountRepository;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
//	private DataSource dataSource;

//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//		this.jdbcTemplate = new JdbcTemplate(dataSource);
//	}

	@Override
	public double getBalance(long accountId) {
		try {
		double balance = jdbcTemplate.queryForObject("SELECT  accountBalance FROM bankAccounts WHERE accountId = ?",
				new Object[] { accountId }, Double.class);
		return balance;
		} catch (DataAccessException e) {
			e.initCause(new EmptyResultDataAccessException("Expected 1 actual 0", 1));
			throw e;
		}
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		try {
		int count = jdbcTemplate.update("UPDATE bankAccounts SET accountBalance = ? WHERE accountId = ?",
				new Object[] { newBalance, accountId });
		return count != 0;
	} catch (DataAccessException e) {
		e.initCause(new EmptyResultDataAccessException("Expected 1 actual 0", 1));
		throw e;
	
	}

}
}