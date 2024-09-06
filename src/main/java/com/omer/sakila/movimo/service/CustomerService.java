package com.omer.sakila.movimo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.omer.sakila.movimo.entity.Customer;
import com.omer.sakila.movimo.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
	
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}
	
	public Customer authenticateUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String customerEmail = authentication.getName();
	    
	    Customer customer = findByEmail(customerEmail);
	    return customer;
	}
}
