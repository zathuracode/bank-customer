package com.vobi.bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) throws Exception{		
		customerService.deleteById(id);
	}
	
	
	@PutMapping
	public CustomerDTO update(@Valid @RequestBody CustomerDTO customerDTO)throws Exception{
		Customer customer=customerMapper.customerDTOtoCustomer(customerDTO);
		customer=customerService.update(customer);
		customerDTO=customerMapper.customerToCustomerDTO(customer);
		
		return customerDTO;
	}
	
	@PostMapping
	public CustomerDTO save(@Valid @RequestBody CustomerDTO customerDTO)throws Exception{
		Customer customer=customerMapper.customerDTOtoCustomer(customerDTO);
		customer=customerService.save(customer);
		customerDTO=customerMapper.customerToCustomerDTO(customer);
		
		return customerDTO;
	}
	
	
	@GetMapping("/{id}")
	public CustomerDTO findById(@PathVariable("id") Integer id) throws Exception{
		//Customer customer=(customerService.findById(id).isPresent()==true)?customerService.findById(id).get():null;
		
		Customer customer=null;
		CustomerDTO customerDTO=null;
		if(customerService.findById(id).isPresent()==true)
			customer=customerService.findById(id).get();
		
		customerDTO=customerMapper.customerToCustomerDTO(customer);
		
		return customerDTO;
	}
	
	@GetMapping()
	public List<CustomerDTO> findAll()throws Exception{
		
		List<Customer> customers=customerService.findAll();
		List<CustomerDTO> customerDTOs=customerMapper.customerListToCustomerDTOList(customers);
		
		return customerDTOs;
	}
	
	

}
