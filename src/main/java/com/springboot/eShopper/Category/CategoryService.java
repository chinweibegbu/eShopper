package com.springboot.eShopper.Category;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<Category> getAllCategorys() {
		return categoryRepository.findAll();
	}

	public void addNewCategory(Category category) {
		categoryRepository.save(category);		
	}

	public void deleteCategory(Integer categoryId) {
		// Get category with the given ID
		boolean exists = categoryRepository.existsById(categoryId);
		
		if(!exists) {
			throw new IllegalStateException("Category with an id of " + categoryId + " does not exist");
		}
		
		// Else, save the category
		categoryRepository.deleteById(categoryId);
	}

	@Transactional
	public void updateCategory(Category category, Integer categoryId) {
		boolean exists = categoryRepository.existsById(categoryId);
		
		if(!exists) {
			throw new IllegalStateException("Category with an id of " + categoryId + " does not exist");
		}
		
		categoryRepository.save(category);
	}
}
