package com.liquorManagementSystem.CustomerService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.liquorManagementSystem.CustomerService.Entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository < Customer,Integer>{


}
