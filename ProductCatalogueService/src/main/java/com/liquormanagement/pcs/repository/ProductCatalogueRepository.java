package com.liquormanagement.pcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liquormanagement.pcs.model.Product;

public interface ProductCatalogueRepository extends JpaRepository<Product, Long>{
	boolean deleteProductById(Long id);
}
