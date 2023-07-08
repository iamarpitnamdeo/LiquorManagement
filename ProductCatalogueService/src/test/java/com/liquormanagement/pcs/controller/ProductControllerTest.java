package com.liquormanagement.pcs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.liquormanagement.pcs.exception.RecordNotFoundException;
import com.liquormanagement.pcs.service.ProductCatalogueServiceImpl;
import com.liquormanagement.pcs.model.Category;
import com.liquormanagement.pcs.model.Product;

public class ProductControllerTest {

	@Mock
	private ProductCatalogueServiceImpl productCatalogueService;
	@InjectMocks
	private ProductController productController;
	
	public ProductControllerTest() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void getAllProducts_ReturnsListOfProducts() throws RecordNotFoundException{
		//Arrange
		List<Product> productList = new ArrayList<>();
		productList.add(new Product(1L,"Product 1","Description 1",10.0,"Brand 1",null));
		productList.add(new Product(2L, "Product 2", "Description 2", 20.0, "Brand 2", null));
		when(productCatalogueService.getAllProducts()).thenReturn(productList);
		
		//Act 
		ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();
	
		//Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(productList,responseEntity.getBody());
	}
	
	@Test
	void getProductById_ValidId_ReturnsProduct() throws RecordNotFoundException{
		Long productId = 1L;
        Product product = new Product(productId, "Product 1", "Description 1", 10.0, "Brand 1", null);
        when(productCatalogueService.getProductById(productId)).thenReturn(product);
        ResponseEntity<Product> responseEntity =productController.getProductById(productId);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
	}
	@Test
	void getProductById_InvalidId_ReturnsNotFound() throws RecordNotFoundException{
		Long productId=1L;
		when(productCatalogueService.getProductById(productId)).thenReturn(null);
        ResponseEntity<Product> responseEntity =productController.getProductById(productId);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
        assertEquals(null,responseEntity.getBody());
	}
	@Test
	void getProductByCategory_AvailableProducts() throws RecordNotFoundException{
		String category ="Whiskey";
		Long categoryId=3L;
		List<Category> categories = new ArrayList<>();
		categories.add(new Category(1L,"Wine","Taste like Syrup"));
		categories.add(new Category(2L,"Rum", "Read in colour"));
		categories.add(new Category(3L,"Whiskey","Yewllow in colour"));
		categories.add(new Category(4L,"Beer","Healthy for health"));
		
		List<Product> products = new ArrayList<>();
		products.add(new Product(1L,"Red Lebel","Very Smooth whiskey",2000.0,"Johnnie Walker",categories.get(2)));
		products.add(new Product(2L,"Blue Lebel","Very Smooth whiskey",4000.0,"Johnnie Walker",categories.get(2)));
		products.add(new Product(3L,"Black Lebel","Very Smooth whiskey",8000.0,"Johnnie Walker",categories.get(2)));
		
		List<Product> filteredProducts = products.stream()
	            .filter(product -> product.getCategory().getId().equals(categoryId))
	            .collect(Collectors.toList());
		
		when(productCatalogueService.getProductByCategory(category)).thenReturn(filteredProducts);
	
		List<Product> result = productController.getProductByCategory(category);
		assertEquals(filteredProducts.size(),result.size());
		assertEquals(filteredProducts.get(0),result.get(0));
	}
	
	@Test
	void getProductByCategory_ThrowsRecordNotFoundException() throws RecordNotFoundException{
		
		String category ="Non-existing category";
		when(productCatalogueService.getProductByCategory(category)).thenThrow(new RecordNotFoundException("No element present with the entered category"));
	
		try {
			productController.getProductByCategory(category); 
		}catch(RecordNotFoundException e) {
			assertEquals("No element present with the entered category",e.getMessage());
		}
	}
}
