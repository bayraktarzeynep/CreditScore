package com.scorechecker.service;

import com.scorechecker.entity.CreditScore;


public interface CreditScoreService {
	 CreditScore saveCreditScore( CreditScore creditScore);
	 CreditScore findByTcIdentity(String tcIdentity);
   	 boolean creditScoreExists(CreditScore c);
   	 void updateCreditScore(CreditScore c);
}
