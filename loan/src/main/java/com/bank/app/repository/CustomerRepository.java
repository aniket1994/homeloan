package com.bank.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.app.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	public List<Customer> findByStatus(String checkS);
	
}
