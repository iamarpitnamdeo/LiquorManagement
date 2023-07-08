package com.liquormanagement.pcs.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import java.net.URI;
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
	void getAllProducts_ReturnsListOfProducts() throws RecordNotFoundException {
		// Arrange
		List<Product> productList = new ArrayList<>();
		productList.add(new Product(1L, "Product 1", "Description 1", 10.0, "Brand 1", null));
		productList.add(new Product(2L, "Product 2", "Description 2", 20.0, "Brand 2", null));
		when(productCatalogueService.getAllProducts()).thenReturn(productList);

		// Act
		ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(productList, responseEntity.getBody());
	}

	@Test
	void getProductById_ValidId_ReturnsProduct() throws RecordNotFoundException {
		Long productId = 1L;
		Product product = new Product(productId, "Product 1", "Description 1", 10.0, "Brand 1", null);
		when(productCatalogueService.getProductById(productId)).thenReturn(product);
		ResponseEntity<Product> responseEntity = productController.getProductById(productId);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(product, responseEntity.getBody());
	}

	@Test
	void getProductById_InvalidId_ReturnsNotFound() throws RecordNotFoundException {
		Long productId = 1L;
		when(productCatalogueService.getProductById(productId)).thenReturn(null);
		ResponseEntity<Product> responseEntity = productController.getProductById(productId);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		assertEquals(null, responseEntity.getBody());
	}

	@Test
	void getProductByCategory_AvailableProducts() throws RecordNotFoundException {
		String category = "Whiskey";
		Long categoryId = 3L;
		List<Category> categories = new ArrayList<>();
		categories.add(new Category(1L, "Wine", "Taste like Syrup"));
		categories.add(new Category(2L, "Rum", "Read in colour"));
		categories.add(new Category(3L, "Whiskey", "Yewllow in colour"));
		categories.add(new Category(4L, "Beer", "Healthy for health"));

		List<Product> products = new ArrayList<>();
		products.add(new Product(1L, "Red Lebel", "Very Smooth whiskey", 2000.0, "Johnnie Walker", categories.get(2)));
		products.add(new Product(2L, "Blue Lebel", "Very Smooth whiskey", 4000.0, "Johnnie Walker", categories.get(2)));
		products.add(
				new Product(3L, "Black Lebel", "Very Smooth whiskey", 8000.0, "Johnnie Walker", categories.get(2)));

		List<Product> filteredProducts = products.stream()
				.filter(product -> product.getCategory().getId().equals(categoryId)).collect(Collectors.toList());

		when(productCatalogueService.getProductByCategory(category)).thenReturn(filteredProducts);

		List<Product> result = productController.getProductByCategory(category);
		assertEquals(filteredProducts.size(), result.size());
		assertEquals(filteredProducts.get(0), result.get(0));
	}

	@Test
	void getProductByCategory_ThrowsRecordNotFoundException() throws RecordNotFoundException {

		String category = "Non-existing category";
		when(productCatalogueService.getProductByCategory(category))
				.thenThrow(new RecordNotFoundException("No element present with the entered category"));

		try {
			productController.getProductByCategory(category);
		} catch (RecordNotFoundException e) {
			assertEquals("No element present with the entered category", e.getMessage());
		}
	}

	@Test
	void addProduct_ReturnsCreatedProduct() {
		Product newProduct = new Product(5L, "Royal RanthomBore", "Do not drinl", 3000.0, "Rajasthan Forest", null);
		when(productCatalogueService.addProduct(newProduct)).thenReturn(newProduct);
		ResponseEntity<Product> responseEntity = productController.addProduct(newProduct);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(newProduct, responseEntity.getBody());
		assertEquals(URI.create("/products" + newProduct.getId()), responseEntity.getHeaders().getLocation());
	}

	@Test
	void updateProduct_ReturnsUpdatedProduct() throws RecordNotFoundException {
		Long productId = 1L;
		Product updatedProduct =  new Product(productId, "Updated Product", "Updated Description",20.0,"updated brand",null);
		when(productCatalogueService.updateProduct(updatedProduct)).thenReturn(updatedProduct);
		ResponseEntity<Product> responseEntity = productController.updateProduct(productId, updatedProduct);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(updatedProduct, responseEntity.getBody());
	}

	@Test
	void updateProduct_ThrowsRecordNotFoundException() throws RecordNotFoundException{
		 Long productId = 1L;
	        Product updatedProduct = new Product(productId, "Updated Product", "Updated Description", 20.0, "Updated Brand", null);
	        when(productCatalogueService.updateProduct(updatedProduct)).thenThrow(new RecordNotFoundException("Product not found"));

	        // Act & Assert
	        try {
	            productController.updateProduct(productId, updatedProduct);
	        } catch (RecordNotFoundException e) {
	            assertEquals("Product not found", e.getMessage());
	        }
	 }
	@Test
	void deleteProduct_ReturnsSuccessMessage() throws RecordNotFoundException {
		Long productId = 1L;
		ResponseEntity<String> responseEntity = productController.deleteProduct(productId);
		assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
		assertEquals("Product deleted successfully",responseEntity.getBody());
	}
	@Test
	void deleteProduct_ThrowsRecordNotFoundException() throws RecordNotFoundException{
		 Long productId = 1L;
	        doThrow(new RecordNotFoundException("Product not found")).when(productCatalogueService).deleteProductById(productId);

	        // Act & Assert
	        try {
	            productController.deleteProduct(productId);
	        } catch (RecordNotFoundException e) {
	            assertEquals("Product not found", e.getMessage());
	        }
	}
}
