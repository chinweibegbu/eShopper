package com.springboot.eShopper.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/categories")
public class CategoryController {
	
	private final CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public List<Category> getAllCategories() {
		return categoryService.getAllCategories();
	}
	
	@GetMapping(path="{categoryId}")
	public Category getAllCategories(@PathVariable("categoryId") Integer categoryId) {
		return categoryService.getCategoryById(categoryId);
	}
	
	@PostMapping
	public void registerNewCategory(@RequestBody Category category) {
		categoryService.addNewCategory(category);
	}
	
	@PutMapping(path="{categoryId}")
	public void updateCategory(@RequestBody Category category, @PathVariable Integer categoryId) {
		categoryService.updateCategory(category, categoryId);
	}
}
