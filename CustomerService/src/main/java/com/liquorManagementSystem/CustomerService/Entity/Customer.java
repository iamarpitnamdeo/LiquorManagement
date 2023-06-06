package com.liquorManagementSystem.CustomerService.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Customer")
@Component
public class Customer {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="cus_id")
private int customerid;
private String customerName;
private String customerEmail;
private int customerPhoneNumber;


public int getCustomerid() {
	return customerid;
}
public void setCustomerid(int customerid) {
	this.customerid = customerid;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getCustomerEmail() {
	return customerEmail;
}
public void setCustomerEmail(String customerEmail) {
	this.customerEmail = customerEmail;
}
public int getCustomerPhoneNumber() {
	return customerPhoneNumber;
}
public void setCustomerPhoneNumber(int customerPhoneNumber) {
	this.customerPhoneNumber = customerPhoneNumber;
}

}
