package com.capgemini.bankapp.service;

import com.capgemini.bankapp.entities.Customer;
import com.capgemini.bankapp.exception.CustomerNotFoundException;

public interface CustomerService {
	public Customer authenticate(Customer customer) throws CustomerNotFoundException;
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);

}