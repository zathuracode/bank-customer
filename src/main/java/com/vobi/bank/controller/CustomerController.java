package com.vobi.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobi.bank.dto.CustomerDTO;
import com.vobi.bank.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping()
	public List<CustomerDTO> findAll()throws Exception{
		
		
		
		return null;
	}

}
