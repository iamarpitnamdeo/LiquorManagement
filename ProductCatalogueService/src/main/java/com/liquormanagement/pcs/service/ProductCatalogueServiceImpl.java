package com.liquormanagement.pcs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liquormanagement.pcs.controller.ProductController;
import com.liquormanagement.pcs.exception.RecordNotFoundException;
import com.liquormanagement.pcs.model.Category;
import com.liquormanagement.pcs.model.Inventory;
import com.liquormanagement.pcs.model.Product;
import com.liquormanagement.pcs.model.Review;
import com.liquormanagement.pcs.repository.ProductCatalogueRepository;
import com.liquormanagement.pcs.repository.ProductCategoryRepository;

@Service
public class ProductCatalogueServiceImpl implements ProductCatalogueService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCatalogueServiceImpl.class);

	@Autowired
	ProductCatalogueRepository productCatalogueRepository;
	
	@Autowired
	ProductCategoryRepository productCategoryRepository;
	
	@Override
	public Product addProduct(Product product) {
		return productCatalogueRepository.save(product);
		
	}
	
	@PostConstruct
	public void initDb() {
		System.out.println("Entered InitiDB");
		List<Category> categories = new ArrayList<>();
		categories.add(new Category(1L,"Wine","Taste like Syrup"));
		categories.add(new Category(2L,"Rum", "Read in colour"));
		categories.add(new Category(3L,"Whiskey","Yewllow in colour"));
		categories.add(new Category(4L,"Beer","Healthy for health"));
		productCategoryRepository.saveAll(categories);
		
		List<Product> products = new ArrayList<>();
		products.add(new Product(1L,"Red Lebel","Very Smooth whiskey",2000.0,"Johnnie Walker",categories.get(2)));
		products.add(new Product(2L,"Blue Lebel","Very Smooth whiskey",4000.0,"Johnnie Walker",categories.get(2)));
		products.add(new Product(3L,"Black Lebel","Very Smooth whiskey",8000.0,"Johnnie Walker",categories.get(2)));
		products.add(new Product(4L,"Chevalier-Montrachet Grand Cru","This white wine has a powerful nose of almond and pear aromas. It has a complex and fine palate, with a good length.",964.0,"Cote de Beaune",categories.get(0)));
		products.add(new Product(5L,"Tenuta San Guido Sassicaia Bolgheri","Wafts of green olive and sage meet the nose. This Super Tuscan wine offers mineral and dark fruit flavors along with invigorating freshness.", 427.0,"Tuscany",categories.get(0)));
		productCatalogueRepository.saveAll(products);
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
		
		/*LOGGER.info("Getting Products list");
		
		System.out.println(productsList.get(0).getBrand());
		
		if(productsList.size()>0)
			return productsList;
		else
			return new ArrayList<Product>();*/
		return productsList;
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
