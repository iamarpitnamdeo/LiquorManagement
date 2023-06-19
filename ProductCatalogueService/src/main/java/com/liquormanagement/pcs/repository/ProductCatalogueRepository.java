package com.liquormanagement.pcs.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.liquormanagement.pcs.model.Product;

public interface ProductCatalogueRepository extends JpaRepository<Product, Long>{
	boolean deleteProductById(Long id);

	//Optional<Product> findByCategory(String category);
	
//	@Query("SELECT pc FROM ProductCatalogue pc WHERE pc.category = :category")
//    Optional<Product> findByCategory(@Param("category") String category);

}
