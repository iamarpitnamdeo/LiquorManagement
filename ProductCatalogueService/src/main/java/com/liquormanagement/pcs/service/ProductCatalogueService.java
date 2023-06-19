package com.liquormanagement.pcs.service;

import java.util.List;

import com.liquormanagement.pcs.exception.RecordNotFoundException;
import com.liquormanagement.pcs.model.Inventory;
import com.liquormanagement.pcs.model.Product;
import com.liquormanagement.pcs.model.Review;

public interface ProductCatalogueService {
	List<Product> getAllProducts();
	Product addProduct(Product product);
	Product updateProduct(Product product) throws RecordNotFoundException;
	Product getProductById(Long id) throws RecordNotFoundException;
	void deleteProductById(Long id) throws RecordNotFoundException;
	List<Review> getProductReviews(Long productId);
	Review addProductReview(String productId, Review review);
	Inventory getProductInventory(String productId);
}
