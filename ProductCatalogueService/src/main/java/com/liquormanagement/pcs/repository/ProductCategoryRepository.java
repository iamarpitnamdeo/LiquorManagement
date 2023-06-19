package com.liquormanagement.pcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.liquormanagement.pcs.model.Category;

public interface ProductCategoryRepository extends JpaRepository<Category,Long>{

	@Query("SELECT c.id FROM Category c WHERE c.name = :name")
    Long findCategoryIdByName(@Param("name") String name);
}
