package com.springboot.eShopper.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.springboot.eShopper.CartItem.CartItem;
import com.springboot.eShopper.Shopper.Shopper;

@RestController
@RequestMapping(path="api/v1/products")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping(path="{productId}")
	public Product getProductById(@PathVariable("productId") Integer productId) {
		return productService.getProductById(productId);
	}
	
	@GetMapping(path="/category/{categoryName}")
	public List<Product> getProductsByCategory(@PathVariable("categoryName") String categoryName) {
		return productService.getProductsByCategory(categoryName);
	}
	
	@GetMapping(path="/search/{searchTerm}")
	public List<Product> searchProductsByProductName(@PathVariable("searchTerm") String searchTerm) {
		return productService.searchProductsByProductName(searchTerm);
	}
	
	@PostMapping
	public void registerNewProduct(@RequestBody Product product) {
		productService.addNewProduct(product);		
	}
	
	@PutMapping(path="/discontinue/{productId}")
	public void discontinueProduct(@PathVariable("productId") Integer productId) {
		productService.discontinueProduct(productId);
	}
	
	@PutMapping(path="{productId}")
	public void updateProduct(@RequestBody Product product, @PathVariable Integer productId) {
		productService.updateProduct(product, productId);
	}
}
