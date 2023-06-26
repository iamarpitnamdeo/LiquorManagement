package com.liquormanagement.SHMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liquormanagement.SHMS.entity.Shop;
import com.liquormanagement.SHMS.repository.ShopRepo;

@Service
public class ShopService {
	
	@Autowired
	private ShopRepo shopRepo;

	public void registeringShop(Shop shop) {
		shopRepo.save(shop);
		
	}

	public void getShopById(Long shopId) {
		shopRepo.findById(shopId);
	}

}
