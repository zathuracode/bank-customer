package com.vobi.bank.mapper;

import org.mapstruct.Mapper;

import com.vobi.bank.domain.Customer;
import com.vobi.bank.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {
	
	
	public CustomerDTO customerToCustomerDTO(Customer customer);

}
 