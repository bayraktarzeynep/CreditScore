package com.scorechecker.service;

import com.scorechecker.entity.Customer;

public interface CustomerService {
	 Customer saveCustomer(Customer customer);
	 Customer findByTcIdentity(String tcIdentity);
	 boolean customerExists(Customer c);
	void updateCustomer(Customer customer);
}
