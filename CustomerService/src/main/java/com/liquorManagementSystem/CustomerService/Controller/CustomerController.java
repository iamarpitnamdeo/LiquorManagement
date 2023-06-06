package com.liquorManagementSystem.CustomerService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.liquorManagementSystem.CustomerService.Entity.Customer;
import com.liquorManagementSystem.CustomerService.Service.CustomerServiceImpl;

@RestController
@RequestMapping("Customer")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl service;

	@GetMapping("/findAllCustomers")
	public ResponseEntity<List<Customer>> findAll() {
		List<Customer> customers = this.service.getAllCustomer();
		ResponseEntity<List<Customer>> response = new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		return response;
	}

	@PostMapping("/add")
	@ResponseStatus(value = HttpStatus.OK)
	public Customer add(@RequestBody Customer customer) {
		Customer cust = service.add(customer);
		return cust;

	}

}
