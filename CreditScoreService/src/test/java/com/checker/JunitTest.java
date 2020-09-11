package com.checker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scorechecker.entity.Customer;
import com.scorechecker.repository.CustomerRepository;
import com.scorechecker.service.CustomerService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
class JunitTest {

	@Autowired
	private CustomerService service;

	@MockBean
	private CustomerRepository repository;

	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Customer(26, "155264555", "Zeynep", "Bayraktar", 2000, "233665555",0),
						new Customer(26, "15526555", "Zülfiye", "Bayraktar", 2000, "23366555",0))
				.collect(Collectors.toList()));
		assertEquals(2, service.findByTcIdentity("23366555").getId());
	}

}
