package com.scorechecker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.scorechecker.entity.CreditScore;
import com.scorechecker.repository.CreditScoreRepository;
import com.scorechecker.service.CreditScoreService;


@Service
public class CreditScoreServiceImpl implements CreditScoreService {
	 @Autowired
	    private CreditScoreRepository creditScoreRepository;
	   
	   

		@Override
		public CreditScore saveCreditScore(CreditScore creditScore) {
			 return creditScoreRepository.save(creditScore);
		}

		@Override
		public CreditScore findByTcIdentity(String tcIdentity) {
			 return creditScoreRepository.findByTcIdentity(tcIdentity);
		}

		@Override
		public boolean creditScoreExists(CreditScore c) {
			 return creditScoreRepository.exists(Example.of(c));
			
		}

		@Override
		public void updateCreditScore(CreditScore c) {
			creditScoreRepository.save(c);
		}

}
