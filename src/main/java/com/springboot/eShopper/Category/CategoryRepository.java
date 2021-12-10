package com.springboot.eShopper.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.eShopper.Shopper.Shopper;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	// Method to get a Category by categoryName
	Optional<Category> getCategoryByCategoryName(String categoryName);
}
