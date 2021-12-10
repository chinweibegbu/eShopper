package com.springboot.eShopper.Product;
import java.beans.Introspector;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.springboot.eShopper.Category.Category;
import com.springboot.eShopper.Category.CategoryRepository;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public Product getProductById(Integer productId) {
		return productRepository.findById(productId).get();
	}
	
	public List<Product> getProductsByCategory(String categoryName) {
		// Get category with given name
		Optional<Category> category  = categoryRepository.getCategoryByCategoryName(categoryName);
		
		// If it does not exist, throw an error
		if(category == null) {
			throw new IllegalStateException("Category named " + categoryName + " does not exist");
		}
		
		// Else, return a list of all categories with that category
		return productRepository.getProductsByCategory(category.get());
	}

	public void addNewProduct(Product product) {
		// Add product to DB
		productRepository.save(product);
		
		// Get category for product
		Category category = product.getCategory();		

		// Add product to category product list
		category.getProducts().add(product);
	}

	public void deleteProduct(Integer productId) {
		// Get product with the given ID
		boolean exists = productRepository.existsById(productId);
		
		if(!exists) {
			throw new IllegalStateException("Product with an id of " + productId + " does not exist");
		}
		
		// Else, save the product
		productRepository.deleteById(productId);
	}

	@Transactional
	public void updateProduct(Product product, Integer productId) {
		boolean exists = productRepository.existsById(productId);
		
		if(!exists) {
			throw new IllegalStateException("Product with an id of " + productId + " does not exist");
		}
		
		// Get product to update
		Product productToUpdate = productRepository.getById(productId);
		
		// Check if the product has new details
		boolean hasNewName = (product.getProductName() != null || (product.getProductName() != productToUpdate.getProductName())) ? true : false;
		boolean hasNewPrice = (product.getProductPrice() != 0.00 || (product.getProductPrice() != productToUpdate.getProductPrice())) ? true : false;
		boolean hasNewStock = (product.getStockCount() != 0 || (product.getStockCount() != productToUpdate.getStockCount())) ? true : false;
		boolean hasNewCategory = (product.getCategory() != null || (product.getCategory() != productToUpdate.getCategory())) ? true : false;
		
		// Call helper update methods
		if(hasNewName) productToUpdate = updateProductName(productId, product.getProductName());
		if(hasNewPrice) productToUpdate = updateProductPrice(productId, product.getProductPrice());
		if(hasNewStock) productToUpdate = updateProductStock(productId, product.getStockCount());
		if(hasNewCategory) productToUpdate = updateProductCategory(productId, product.getCategory());
		
		// Update product in DB
		productRepository.save(productToUpdate);
	}
	
	public Product updateProductName(Integer productId, String newProductName) {
		Product productToUpdate = productRepository.getById(productId);
		productToUpdate.setProductName(newProductName);
		
		return productToUpdate;
	}
	
	public Product updateProductCategory(Integer productId, Category newCategory) {
		// Get product to update
		Product productToUpdate = productRepository.getById(productId);
		
		// Remove product from old category product list
		Category oldCategory = productToUpdate.getCategory();
		oldCategory.getProducts().remove(productToUpdate);
				
		// Update product's category
		productToUpdate.setCategory(newCategory);
		
		// Add product to new category product list
		Category updatedProductCategory = productToUpdate.getCategory();
		updatedProductCategory.getProducts().add(productToUpdate);
		
		categoryRepository.save(updatedProductCategory);
		categoryRepository.save(oldCategory);
		
		return productToUpdate;
	}

	public Product updateProductPrice(Integer productId, Double newProductPrice) {
		Product productToUpdate = productRepository.getById(productId);
		productToUpdate.setProductPrice(newProductPrice);
		
		return productToUpdate;
	}

	public Product updateProductStock(Integer productId, Integer newProductStock) {
		Product productToUpdate = productRepository.getById(productId);
		productToUpdate.setStockCount(newProductStock);
		
		return productToUpdate;
	}
}
