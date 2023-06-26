package com.liquormanagement.SHMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liquormanagement.SHMS.entity.Shop;
import com.liquormanagement.SHMS.service.ShopService;

@RestController
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	
	@PostMapping(value = "/shopRegister")
	public void registerShop(Shop shop) {
		shopService.registeringShop(shop);
	}
	
	@GetMapping(value = "/getShop/{shopId}")
	public void getShop(@PathVariable("shopId") Long shopId) {
		shopService.getShopById(shopId);
	}

}
