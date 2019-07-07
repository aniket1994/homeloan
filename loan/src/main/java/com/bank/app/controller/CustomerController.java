package com.bank.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.dto.CustomerDTO;
import com.bank.app.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customer")
	public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {

		String mes = customerService.createCustomer(customerDTO);
		return new ResponseEntity(mes, HttpStatus.CREATED);
	}
}
