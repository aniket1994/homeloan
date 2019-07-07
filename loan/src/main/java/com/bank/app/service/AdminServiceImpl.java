package com.bank.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.app.dto.AdminDTO;
import com.bank.app.entity.Admin;
import com.bank.app.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public String loginAdmin(AdminDTO adminDTO) {

		String returnValue = "login failed";
		Admin adminLogin = adminRepository.findByUserName(adminDTO.getUserName());
		if(adminLogin != null) {
			returnValue = "loginSucess";
		}
		return returnValue;
	}

}
