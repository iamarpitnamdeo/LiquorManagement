package com.liquormanagement.pcs.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.liquormanagement.pcs.model.Product;

public interface ProductCatalogueRepository extends JpaRepository<Product, Long>{
	boolean deleteProductById(Long id);
}
