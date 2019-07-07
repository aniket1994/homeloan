package com.bank.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.dto.AdminDTO;
import com.bank.app.entity.Customer;
import com.bank.app.service.AdminService;
import com.bank.app.service.CustomerService;

@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CustomerService customerService;

	@PutMapping("/admin")
	public ResponseEntity<String> adminLogin(@RequestBody AdminDTO adminDTO) {
		//to do httpHeader
		String mes = adminService.loginAdmin(adminDTO);
		return new ResponseEntity<String>(mes, HttpStatus.OK);
	}

	@PostMapping("/action/{id}")
	public ResponseEntity<String> adminAction(@PathVariable int id) {
		String mes = customerService.findCustomer(id);
		return new ResponseEntity<String>(mes, HttpStatus.OK);
	}

	@GetMapping("/showStatus/{status}")
	public ResponseEntity<List<Customer>> showWaitingCust(@PathVariable String status) {
		List<Customer> listCustomer = customerService.showStatus(status);
		return new ResponseEntity(listCustomer, HttpStatus.OK);
	}
}