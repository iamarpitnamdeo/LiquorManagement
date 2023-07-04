package com.liquormanagement.SHMS.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liquormanagement.SHMS.entity.Shop;
import com.liquormanagement.SHMS.exception.RecordNotFoundException;
import com.liquormanagement.SHMS.service.ShopService;

@RestController
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value= "/getShop")
	public  ResponseEntity<List<Shop>> getAllShops(){
		List<Shop> allShops= null;
		try {
			allShops = shopService.getAllShops();
		}catch(RecordNotFoundException e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(allShops,HttpStatus.OK);
	}
	
	@PostMapping(value = "/shopRegister")
	public ResponseEntity<Shop> registerShop(@RequestBody Shop shop) {
		Shop addedShop = shopService.registeringShop(shop);
		return ResponseEntity.created(URI.create("/getShop"+addedShop.getShopId())).body(addedShop);
	}
	
	@GetMapping(value = "/getShop/{shopId}")
	public ResponseEntity<Shop> getShopById(@PathVariable("shopId") Long shopId) throws RecordNotFoundException {
		Shop shop = shopService.getShopById(shopId);
		if(shop!=null) {
			return ResponseEntity.ok(shop);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value="/getShop/{shopName}")
	public ResponseEntity<List<Shop>> getShopByName(@PathVariable("shopName") String shopName) throws RecordNotFoundException{
		List<Shop> shopsByName = shopService.getShopsByName(shopName);
		return (ResponseEntity<List<Shop>>) shopsByName;
	}

}
