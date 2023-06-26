package com.liquormanagement.SHMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.liquormanagement.SHMS.entity.Shop;

public interface ShopRepo extends JpaRepository<Shop, Long> {

}
