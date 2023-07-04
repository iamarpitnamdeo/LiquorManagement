package com.liquormanagement.SHMS.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long shopId;

	private String shopName;



	private String street;
	private String city;
	private String state;
	private String zipCode;


	
//	public Shop(Long shopId, String shopName, int pincode, Address address) {
//		super();
//		this.shopId = shopId;
//		this.shopName = shopName;
//		this.pincode = pincode;
//		this.address = address;
//	}
	
	

//	public Shop(String shopName, int pincode, Address address) {
//		super();
//		this.shopName = shopName;
//		this.pincode = pincode;
//		this.address = address;
//	}

	public Shop() {
		super();
	}


	public Shop(String shopName, String street, String city, String state,
			String zipCode) {
		super();
		this.shopName = shopName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}


	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Long getShopId() {
		return shopId;
	}
//	public Shop(Long shopId, String shopName, int pincode) {
//		super();
//		this.shopId = shopId;
//		this.shopName = shopName;
//		this.pincode = pincode;
//	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}


//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}

}
