package com.capgemini.bankapp.repository;

import com.capgemini.bankapp.entities.Customer;

public interface CustomerRepository {
	public Customer authenticate(Customer customer) ;
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword);
}