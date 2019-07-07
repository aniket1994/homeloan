package com.bank.app.service;

import java.util.List;

import com.bank.app.dto.CustomerDTO;
import com.bank.app.entity.Customer;

public interface CustomerService {
	
	public String createCustomer(CustomerDTO customerDTO);
	
	public List<Customer> showStatus(String status);
	
	public String findCustomer(int id);
	
	

}
