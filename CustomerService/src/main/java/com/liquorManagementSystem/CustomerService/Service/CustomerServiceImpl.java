package com.liquorManagementSystem.CustomerService.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.liquorManagementSystem.CustomerService.Entity.Customer;
@Service
public interface CustomerServiceImpl {

	public Customer add(Customer customer);
	public Customer findCustomerByID(int customerId);
	public Customer deleteCustomer(int customerId);
	public List<Customer> getAllCustomer();
	
}