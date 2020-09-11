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
import com.scorechecker.model.calculateLimitRequest;
import com.scorechecker.model.calculateLimitResponse;
import com.scorechecker.service.CreditScoreService;
import com.scorechecker.service.CustomerService;

@RestController
@RequestMapping("/creditscores")
public class CreditScoreController {
 
    @Autowired
    private CreditScoreService creditScoreService;
    
   @Autowired
   private CustomerService customerService;
 
    @RequestMapping(value = "/creditscore/{tcidentity}", method = RequestMethod.GET)
    public HttpEntity<CreditScore> getCustomerByTcIdentity(@PathVariable("tcidentity") String tcIdentity) {
        CreditScore creditScore = creditScoreService.findByTcIdentity(tcIdentity);
        if (creditScore == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(creditScore, HttpStatus.OK);
        }
    }
 
    @RequestMapping(value = "/creditscores/", method = RequestMethod.POST)
    public HttpEntity<?> saveCreditScore(@RequestBody CreditScore c) {
        if (creditScoreService.creditScoreExists(c)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
        	CreditScore creditScore = creditScoreService.saveCreditScore(c);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/customers/customer/{tcidentity}")
                    .buildAndExpand(creditScore.getId()).toUri();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(location);
            return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
        }
    }
    
    @RequestMapping(value = "/creditscore/{tcidentity}", method = RequestMethod.PUT)
    public HttpEntity<?> updateCreditScore(@PathVariable("tcidentity") String tcidentity, @RequestBody CreditScore cs) {
    	
    	
    	CreditScore creditscore =creditScoreService.findByTcIdentity(tcidentity);
        if(creditscore == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
        	
            creditScoreService.updateCreditScore(creditscore);
            return new ResponseEntity<>(creditScoreService, HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/creditscore", method = RequestMethod.PUT)
    public HttpEntity<?> updateCustomerLimit( @RequestBody calculateLimitRequest cs) {
   
    	
    	calculateLimitResponse calculateResponse=new calculateLimitResponse();
    	
    	CreditScore creditscore =creditScoreService.findByTcIdentity(cs.getTcIdentity());
        if(creditscore == null){
        	calculateResponse.setApproveStatus("DECLİNED");
        } else {
        	int multip=4;
        	long newLimit=0;
        	if (creditscore.getScore() < 500){
				calculateResponse.setApproveStatus("DECLİNED");
			}
        	if(creditscore.getScore() >= 500 && creditscore.getScore()<1000) {
        		if(cs.getMonthlyIncome() < 5000) {
        			calculateResponse.setApproveStatus("APPROVED");
        			newLimit=10000;
        			calculateResponse.setNewLimit(newLimit);
        		}
        		else {
        			// Kredi skoru 500 puan ile 1000 puan arasında ise ve aylık geliri 5000'den büyük ise
        			//Bu case belirtilmemiştir.
        			calculateResponse.setApproveStatus("APPROVED");
        			newLimit= multip*cs.getMonthlyIncome();
        			calculateResponse.setNewLimit(newLimit);
        			
        		}
        	}
        	
        	else {
        		newLimit= multip*cs.getMonthlyIncome();
        		calculateResponse.setApproveStatus("APPROVED");
        		calculateResponse.setNewLimit(newLimit);
        		
			}
        	Customer updatecustomer = customerService.findByTcIdentity(cs.getTcIdentity()); 
        	updatecustomer.setTcIdentity(cs.getTcIdentity());
        	updatecustomer.setId(updatecustomer.getId());
        	updatecustomer.setMonthlyIncome(updatecustomer.getMonthlyIncome());
        	updatecustomer.setLimit(newLimit);
        	customerService.updateCustomer(updatecustomer);
            
        }
        
        return new ResponseEntity<>(calculateResponse, HttpStatus.OK);
		
    }
    
    
}