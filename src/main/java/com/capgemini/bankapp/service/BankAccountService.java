package com.capgemini.bankapp.service;

import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.exception.PayeeAccountNotFoundException;

public interface BankAccountService {

	
	public double getBalance(long accountId);
	public double withdraw(long accountId, double amount) throws LowBalanceException;
	public double deposit(long accountId, double amount);
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws LowBalanceException;

}