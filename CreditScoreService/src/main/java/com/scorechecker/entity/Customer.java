package com.scorechecker.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EntityScan
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Customer {
	@Transient
    public static final String SEQUENCE_NAME = "customers_sequence";
	@Id
	long id;
	String tcIdentity;
	String name;
	String surname;
	long monthlyIncome;
	String phoneNumber;
	long limit;
	

}