package com.liquorManagementSystem.CustomerService.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liquorManagementSystem.CustomerService.Entity.Customer;
import com.liquorManagementSystem.CustomerService.Repository.CustomerRepo;

@Service
public class CustomerService implements CustomerServiceImpl {
	
	@Autowired
	private CustomerRepo repository;

	@Override
	public Customer add(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomerByID(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer deleteCustomer(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> cust=this.repository.findAll();
		return cust;
	}

}
