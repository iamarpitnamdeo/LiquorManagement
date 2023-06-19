package com.liquormanagement.pcs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.liquormanagement.pcs.exception.RecordNotFoundException;
import com.liquormanagement.pcs.model.Inventory;
import com.liquormanagement.pcs.model.Product;
import com.liquormanagement.pcs.model.Review;
import com.liquormanagement.pcs.repository.ProductCatalogueRepository;

@Service
public class ProductCatalogueServiceImpl implements ProductCatalogueService{
	@Autowired
	ProductCatalogueRepository productCatalogueRepository;
	
	@Override
	public Product addProduct(Product product) {
		return productCatalogueRepository.save(product);
	}
	@Override
	public Product updateProduct(Product product) throws RecordNotFoundException{
		Optional<Product> productToUpdate = productCatalogueRepository.findById(product.getId());
		if(productToUpdate.isPresent()) {
			Product newProduct = productToUpdate.get();
			newProduct.setName(product.getName());
			newProduct.setBrand(product.getBrand());
			newProduct.setDescription(product.getDescription());
			newProduct.setPrice(product.getPrice());
			
			newProduct = productCatalogueRepository.save(newProduct);
			return newProduct;
		}else {
			throw new RecordNotFoundException("No Product Record exist for given id");
		}
	}
	
	/*
	 * public Product createOrUpdateProduct(Product product)throws RecordNotFoundException{
		Optional<Product> productToUpdate = productCatalogueRepository.findById(product.getId());
		if(productToUpdate.isPresent()) {
			Product newProduct = productToUpdate.get();
			newProduct.setName(product.getName());
			newProduct.setBrand(product.getBrand());
			newProduct.setDescription(product.getDescription());
			newProduct.setPrice(product.getPrice());
			
			newProduct = productCatalogueRepository.save(newProduct);
			return newProduct;
		}else {
			product = productCatalogueRepository.save(product);
			return product;
		}
	}
	*/
	@Override
	public List<Product> getAllProducts() {
		
		List<Product> productsList= productCatalogueRepository.findAll();
		if(productsList.size()>0)
			return productsList;
		else
			return new ArrayList<Product>();
	}



	@Override
	public Product getProductById(Long id) throws RecordNotFoundException {
		Optional<Product> product = productCatalogueRepository.findById(id);
		
		if(product.isPresent()) {
			return product.get();
		}else {
			throw new RecordNotFoundException("No Product Record exist for given id");
		}
	
	}



	@Override
	public List<Review> getProductReviews(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductById(Long id) throws RecordNotFoundException{	
		Optional<Product> product = productCatalogueRepository.findById(id);
		if(product.isPresent()) {
		productCatalogueRepository.deleteById(id);	
		}
		else {
			throw new RecordNotFoundException("No record exist for the given id");
		}
			
	}
	
	@Override
	public Review addProductReview(String productId, Review review) {
		// TODO Auto-generated method stub
		return null;
	}

	public Inventory getProductInventory(String productId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
