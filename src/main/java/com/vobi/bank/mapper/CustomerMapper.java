package com.vobi.bank.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vobi.bank.domain.Customer;
import com.vobi.bank.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {
	
	
	@Mapping(source = "documentType.dotyId" , target ="dotyId" )
	public CustomerDTO customerToCustomerDTO(Customer customer);

}
 