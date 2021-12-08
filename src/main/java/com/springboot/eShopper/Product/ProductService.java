package com.springboot.eShopper.Product;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class ProductService {
	
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public void addNewProduct(Product product) {
		productRepository.save(product);		
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
		
		productRepository.save(product);
	}
}
