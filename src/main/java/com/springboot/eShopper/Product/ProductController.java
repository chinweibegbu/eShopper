package com.springboot.eShopper.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	
	@PostMapping
	public void registerNewProduct(@RequestBody Product product) {
		productService.addNewProduct(product);
	}
	
	@DeleteMapping(path="{productId}")
	public void deleteProduct(@PathVariable("productId") Integer productId) {
		productService.deleteProduct(productId);
	}
	
	@PutMapping(path="{productId}")
	public void updateProduct(@RequestBody Product product, @PathVariable Integer productId) {
		productService.updateProduct(product, productId);
	}
}
