package com.liquormanagement.SHMS.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.liquormanagement.SHMS.entity.Product;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liquormanagement.SHMS.entity.Shop;
import com.liquormanagement.SHMS.exception.RecordNotFoundException;
import com.liquormanagement.SHMS.repository.ShopRepo;

import jakarta.annotation.PostConstruct;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ShopService {
	
	@Autowired
	private ShopRepo shopRepo;

	@Autowired
	RestTemplate restTemplate;
	
	@PostConstruct
	public void initDb() {
		List<Shop> shops = new ArrayList<>();
		shops.add(new Shop("Navodaya Wines","street" ,"city","state","560029"));
		shops.add(new Shop("CHOPINE - a home for wine lovers","street" ,"city","state","560029"));
		shops.add(new Shop("Venus Wine Boutique", "street" ,"city","state","560029"));
		shops.add(new Shop("Sri Ranga wines mrp outlet","street" ,"city","state","560029"));
		shops.add(new Shop("R.P Wines M.R.P Outlet", "street" ,"city","state","560029"));
		shops.add(new Shop("Pushpa spirits", "street" ,"city","state","560029"));
		
		shopRepo.saveAll(shops);
	}
	
	public List<Shop> getAllShops() throws RecordNotFoundException{
			List<Shop> shoplist = shopRepo.findAll();
			if(shoplist.size()>0) {
				return shoplist;
			}else {
				return new ArrayList<Shop>();
			}
	}
	
	public Shop getShopById(Long shopId) throws RecordNotFoundException{
		Optional<Shop> shop = shopRepo.findById(shopId);
		if(shop.isPresent()) {
			return shop.get();
		}else {
			throw new RecordNotFoundException("Shop for the givedn id does not exist");
		}
	}
	
	public List<Shop> getShopsByName(String name) throws RecordNotFoundException{
		List<Shop> shop = shopRepo.findByShopName(name);
		if(shop.size()>0) {
			return shop;
		}
		else {
			return new ArrayList<Shop>();
		}
	}
	
	public Shop registeringShop(Shop shop) {
		return shopRepo.save(shop);
		
	}

	public Shop updateShop(Shop shop) throws RecordNotFoundException{
		Optional<Shop> shopToUpdate = shopRepo.findById(shop.getShopId());
		if(shopToUpdate.isPresent()) {
			Shop newShop = shopToUpdate.get();
			newShop.setShopName(shop.getShopName());
			newShop.setState(shop.getState());
			newShop.setCity(shop.getCity());
			newShop.setStreet(shop.getStreet());
			newShop.setZipCode(shop.getZipCode());
			return newShop;
		}else {
			throw new RecordNotFoundException("No Product Record exist for given id");
		}
	}

	public void deleteShopByShopId(Long id)throws RecordNotFoundException{
		Optional<Shop> shop = shopRepo.findById(id);
		if(shop.isPresent()){
			shopRepo.deleteById(id);
		}else{
			throw new RecordNotFoundException("No record esist with the entered id");
		}
	}

	@Autowired
	private DiscoveryClient discoveryClient;

	@Value("${pivotal.productcatalogueservice.name}")
	protected String productcatalogueservice;

	public ModelAndView getProducts(Long shopId) throws RecordNotFoundException {
		ModelAndView mv = new ModelAndView();
		mv.addObject("shop",this.getShopById(shopId));
		//getting instances using discoveryClient object
		List<ServiceInstance> instances = discoveryClient.getInstances(productcatalogueservice);
		URI uri = instances.get(0).getUri();
		String url = uri.toString()+"/products";
		List<Product> products = restTemplate.getForObject(url,List.class);
		System.out.println("Details of result "+products.toString());
		mv.addObject("products",products);
		mv.setViewName("shopProducts");
		return mv;
	}


}
