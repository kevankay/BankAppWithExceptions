package com.capgemini.bankapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp.exception.LowBalanceException;

import com.capgemini.bankapp.repository.BankAccountRepository;
import com.capgemini.bankapp.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {
	@Autowired
	private BankAccountRepository bankAccountRepository;

	

	@Override
	public double getBalance(long accountId) {
		return bankAccountRepository.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException {
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != 0) {
			if (balance - amount >= 0) {
				bankAccountRepository.updateBalance(accountId, (balance - amount));
				return bankAccountRepository.getBalance(accountId);
			} else {
				throw new LowBalanceException("You don't have sufficient fund.");
				
			}
		}
		return balance;
	}

	@Override
	public double deposit(long accountId, double amount) {
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != 0) {
			bankAccountRepository.updateBalance(accountId, balance + amount);
			return bankAccountRepository.getBalance(accountId);
		}
		return balance;
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws LowBalanceException {
		double balance = withdraw(fromAcc, amount);
		if (balance != 0) {
			if (deposit(toAcc, amount) == -1) {
				deposit(fromAcc, amount);
				return false;
			}
			return true;
		}
		return false;
	}

}