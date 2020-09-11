package com.scorechecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.scorechecker.entity.CreditScore;
import com.scorechecker.entity.Customer;
import com.scorechecker.repository.CreditScoreRepository;
import com.scorechecker.repository.CustomerRepository;
import com.scorechecker.service.SequenceGeneratorService;

@SpringBootApplication
public class CreditScoreServiceApplication {
@Autowired
SequenceGeneratorService sequenceService;
	public static void main(String[] args) {
		SpringApplication.run(CreditScoreServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner init(CustomerRepository customerRepository,CreditScoreRepository creditScoreRepository) {
		return (args) -> {
			customerRepository.deleteAll();
			Customer customer1 =new Customer();
			customer1.setId(sequenceService.generateSequence(Customer.SEQUENCE_NAME));
			customer1.setMonthlyIncome(2000);
			customer1.setName("Zeynep");
			customer1.setSurname("Bayraktar");
			customer1.setTcIdentity("19388136256");
			customer1.setPhoneNumber("5324276455");
			customer1.setLimit(0L);
			customerRepository.save(customer1);
			
			
			
			Customer customer2 =new Customer();
			customer2.setId(sequenceService.generateSequence(Customer.SEQUENCE_NAME));
			customer2.setMonthlyIncome(2000);
			customer2.setName("Ali");
			customer2.setSurname("Öztürk");
			customer2.setTcIdentity("19466133610");
			customer2.setPhoneNumber("5387963499");
			customer2.setLimit(0L);
			customerRepository.save(customer2);
			
			creditScoreRepository.deleteAll();
			CreditScore creditScore1=new CreditScore();
			creditScore1.setId(sequenceService.generateSequence(CreditScore.SEQUENCE_NAME));
			creditScore1.setTcIdentity("19466133610");
			creditScore1.setScore(16);
			creditScoreRepository.save(creditScore1);
		
			CreditScore creditScore2=new CreditScore();
			creditScore2.setId(sequenceService.generateSequence(CreditScore.SEQUENCE_NAME));
			creditScore2.setTcIdentity("19388136256");
			creditScore2.setScore(500);
			creditScoreRepository.save(creditScore2);
			
			

		};
	}
}
