package com.liquormanagement.SHMS.entity;

import jakarta.persistence.*;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String brand;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
	
	//private Category category_id;
	//image url
	//created at
	// updated at
	
	public Product() {

	}


	public Product(Long id, String name, String description, Double price, String brand, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.brand = brand;
		this.category = category;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}

	


	public Category getCategory() {
		// TODO Auto-generated method stub
		return category;
	}
	
	public void setCategory(Category category) {
	    this.category = category;
	}
	
}
