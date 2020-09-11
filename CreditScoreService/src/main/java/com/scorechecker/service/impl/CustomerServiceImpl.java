package com.scorechecker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.scorechecker.entity.Customer;
import com.scorechecker.repository.CustomerRepository;
import com.scorechecker.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
 
    @Autowired
    private CustomerRepository customerRepository;
   
   

	@Override
	public Customer saveCustomer(Customer customer) {
		 return customerRepository.save(customer);
	}

	@Override
	public Customer findByTcIdentity(String tcIdentity) {
		 return customerRepository.findByTcIdentity(tcIdentity);
	}
	@Override
	public void updateCustomer(Customer customer) {
		  customerRepository.save(customer);
	}

	@Override
	public boolean customerExists(Customer c) {
		 return customerRepository.exists(Example.of(c));
	
	}
 
   

}
