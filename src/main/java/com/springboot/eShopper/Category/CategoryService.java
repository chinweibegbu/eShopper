package com.springboot.eShopper.Category;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.springboot.eShopper.Category.Category;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	public Category getCategoryById(Integer categoryId) {
		return categoryRepository.findById(categoryId).get();
	}

	public void addNewCategory(Category category) {
		categoryRepository.save(category);		
	}

	@Transactional
	public void updateCategory(Category category, Integer categoryId) {
		boolean exists = categoryRepository.existsById(categoryId);
		
		if(!exists) {
			throw new IllegalStateException("Category with an id of " + categoryId + " does not exist");
		}
		
		// Get category to update
		Category categoryToUpdate = categoryRepository.getById(categoryId);
		
		// Get new categoryName
		String newCategoryName = category.getCategoryName();
		
		// Validate new categoryName
		if(newCategoryName == "") {
			throw new IllegalStateException("Category name must not be empty");
		} else if(newCategoryName == categoryToUpdate.getCategoryName()) {
			throw new IllegalStateException("Category name is the same");
		} else {
			categoryToUpdate.setCategoryName(newCategoryName);
		}				
		
		categoryRepository.save(category);
	}
}
