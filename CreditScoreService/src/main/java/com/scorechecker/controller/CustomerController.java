package com.scorechecker.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.scorechecker.entity.CreditScore;
import com.scorechecker.entity.Customer;
import com.scorechecker.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
 
    @Autowired
    private CustomerService customerService;
 
    @RequestMapping(value = "/customer/{tcidentity}", method = RequestMethod.GET)
    public HttpEntity<Customer> getCustomerByTcIdentity(@PathVariable("tcidentity") String tcIdentity) {
        Customer customer = customerService.findByTcIdentity(tcIdentity);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }
 
    @RequestMapping(value = "/customer/", method = RequestMethod.POST)
    public HttpEntity<?> saveCustomer(@RequestBody Customer c) {
        if (customerService.customerExists(c)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
        	Customer customer = customerService.saveCustomer(c);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/customers/customer/{tcidentity}")
                    .buildAndExpand(customer.getId()).toUri();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(location);
            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        }
    }
    @RequestMapping(value = "/customer/", method = RequestMethod.PUT)
    public HttpEntity<?> updateCustomer(@RequestBody Customer c) {
    	Customer customer =customerService.findByTcIdentity(c.getTcIdentity());
        if(customer == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
        	
            customerService.updateCustomer(c);
            return new ResponseEntity<>(customerService, HttpStatus.OK);
        }
    }

}
