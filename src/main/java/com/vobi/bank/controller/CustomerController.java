package com.vobi.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vobi.bank.domain.Customer;
import com.vobi.bank.dto.CustomerDTO;
import com.vobi.bank.mapper.CustomerMapper;
import com.vobi.bank.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerMapper customerMapper;
	
	@GetMapping("/{id}")
	public CustomerDTO findById(@PathVariable("id") Integer id) throws Exception{
		//Customer customer=(customerService.findById(id).isPresent()==true)?customerService.findById(id).get():null;
		
		Customer customer=null;
		if(customerService.findById(id).isPresent()==true)
			customer=customerService.findById(id).get();
		
		return customerMapper.customerToCustomerDTO(customer);
	}
	
	@GetMapping()
	public List<CustomerDTO> findAll()throws Exception{
		
		List<Customer> customers=customerService.findAll();
		List<CustomerDTO> customerDTOs=customerMapper.customerListToCustomerDTOList(customers);
		
		return customerDTOs;
	}
	
	

}
