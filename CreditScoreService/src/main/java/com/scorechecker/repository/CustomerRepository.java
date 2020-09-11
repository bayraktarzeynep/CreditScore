package com.scorechecker.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.scorechecker.entity.Customer;
public interface CustomerRepository extends MongoRepository<Customer, String> {

	Customer findByTcIdentity(String tcIdentity);

	
}
