package com.capgemini.bankapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp.entities.Customer;
import com.capgemini.bankapp.exception.CustomerNotFoundException;
import com.capgemini.bankapp.exception.PasswordChangeFailedException;
import com.capgemini.bankapp.exception.UpdationFailedException;
import com.capgemini.bankapp.repository.CustomerRepository;
import com.capgemini.bankapp.repository.impl.CustomerRepositoryImpl;
import com.capgemini.bankapp.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired	
	private CustomerRepository customerRepository;
		
	

		@Override
		public Customer authenticate(Customer customer)throws CustomerNotFoundException {
			try {
			return customerRepository.authenticate(customer);
			}catch (DataAccessException ex) {
				CustomerNotFoundException u = new CustomerNotFoundException("Customer not found");
				u.initCause(ex);
				throw u;
			}
			
		}

		@Override
		public Customer updateProfile(Customer customer) {
			try {
			return customerRepository.updateProfile(customer);
			} catch (DataAccessException e) {
				UpdationFailedException updationFailedException = new UpdationFailedException(
						"failed to update the customer details");
				updationFailedException.initCause(e);
				throw updationFailedException;
			}
		}

		@Override
		public boolean updatePassword(Customer customer, String oldPassword, String newPassword) {
			try {
			return customerRepository.updatePassword(customer, oldPassword, newPassword);
		 } catch (DataAccessException e) {
			PasswordChangeFailedException passwordChangeFailedException = new PasswordChangeFailedException(
					"Failed to change the password");
			passwordChangeFailedException.initCause(e);
			throw e;
		}

	}
}
