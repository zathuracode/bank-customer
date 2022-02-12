package com.vobi.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vobi.bank.domain.Customer;
import com.vobi.bank.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	Validator validator;

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Customer> findById(Integer id) {
		return customerRepository.findById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return customerRepository.count();
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Customer save(Customer entity) throws Exception {
		if(entity==null) {
			throw new Exception("El customer es nulo");
		}
		
		validate(entity);
		
		if(customerRepository.existsById(entity.getCustId())) {
			throw new Exception("El customer ya existe");
		}
		
		return customerRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Customer update(Customer entity) throws Exception {
		if(entity==null) {
			throw new Exception("El customer es nulo");
		}
		
		validate(entity);
		
		if(customerRepository.existsById(entity.getCustId())==false) {
			throw new Exception("El customer no existe");
		}
		
		return customerRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void delete(Customer entity) throws Exception {
		
		if(entity==null) {
			throw new Exception("El customer es nulo");
		}
		
		if(entity.getCustId()==null) {
			throw new Exception("El customer id es nulo");
		}
		
		if(customerRepository.existsById(entity.getCustId())==false) {
			throw new Exception("El customer no existe");
		}
		
		findById(entity.getCustId()).ifPresent(customer->{
			if(customer.getAccounts()!=null && customer.getAccounts().isEmpty()==false) {
				throw new RuntimeException("El customer tiene cuentas asociadas");
			}
			
			if(customer.getRegisteredAccounts()!=null && customer.getRegisteredAccounts().isEmpty()==false) {
				throw new RuntimeException("El customer tiene cuentas registradas asociadas");
			}		
		});
		
		customerRepository.deleteById(entity.getCustId());
		
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if(id==null)
			throw new Exception("El id es nulo");
		
		if(customerRepository.existsById(id)==false) {
			throw new Exception("El customer no existe");
		}
		
		delete(customerRepository.findById(id).get());	
	}

	@Override
	public void validate(Customer entity) throws Exception {
		Set<ConstraintViolation<Customer>> constraintViolations=validator.validate(entity);
		if(constraintViolations.isEmpty()==false) {
			throw new ConstraintViolationException(constraintViolations);
		}		
	}

	

}
