package com.bank.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.app.dto.CustomerDTO;
import com.bank.app.entity.Customer;
import com.bank.app.entity.Loan;
import com.bank.app.exception.CustomerException;
import com.bank.app.repository.CustomerRepository;
import com.bank.app.repository.LoanRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private LoanRepository loanRepository;

	@Override
	public String createCustomer(CustomerDTO customerDTO) {

		String returnvalue = "submitted";
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);
		customer.setStatus("PROGRESS");
		System.out.println(customer.getStatus());
		if(customerRepository.save(customer) == null) {
			returnvalue = "Not Submited";
		}
		return returnvalue;
	}

	@Override
	public List<Customer> showStatus(String status) {
		List<Customer> listCustomer = customerRepository.findByStatus(status);
		return listCustomer;
	}

	@Override
	public String findCustomer(int id) {
		String mes = "";
		String accountNumber = "";
		Optional<Customer> customer = customerRepository.findById(id);
		// condition 1 ....only one home loan
		if (customer.isPresent()) {
			Loan existingUser = loanRepository.findByAdharNumber(customer.get().getAdharNumber());
			System.out.println(existingUser == null);
			if (existingUser == null) {
				// condition 2 ......loan amount 2 time security
				System.out.println((2 * customer.get().getLoanAmount()) <= (customer.get().getSecurityAmount()));
				if ((2 * customer.get().getLoanAmount()) > (customer.get().getSecurityAmount())) {
					mes = "REJECTED";
					customer.get().setStatus("REJECTED");
					customerRepository.save(customer.get());
					throw new CustomerException("security amount is less");
				} else if (customer.get().getCreditScore() < 600) {
					mes = "REJECTED";
					customer.get().setStatus("REJECTED");
					customerRepository.save(customer.get());
					throw new CustomerException("less credit score");
				} else {
					Loan loan = new Loan();
					accountNumber = customer.get().getAdharNumber() + customer.get().getName().substring(0, 2);
					loan.setSecurityAmount(customer.get().getSecurityAmount());
					loan.setAccountNumber(accountNumber);
					loan.setAdharNumber(customer.get().getAdharNumber());
					loan.setLoanAmount(customer.get().getLoanAmount());
					loan.setName(customer.get().getName());
					loanRepository.save(loan);
					customer.get().setStatus("APPROVED");
					customerRepository.save(customer.get());
					mes = "APPROVED";
				}
			} else {
				mes = "ALL READY HAVE ONE HOUSE LOAN";
				throw new CustomerException("all ready exists");
			}
		} else {
			throw new CustomerException("no id availabale");
		}
		return mes;
	}
}