package com.liquormanagement.pcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liquormanagement.pcs.model.Category;

public interface ProductCategoryRepository extends JpaRepository<Category,Long>{

}
