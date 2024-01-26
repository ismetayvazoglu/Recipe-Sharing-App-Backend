package com.example.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Recipe;

@Repository
public interface RecipeDAO extends MongoRepository<Recipe, String> {
	
    // You can add custom query methods here if needed
	
    List<Recipe> findByTypeIgnoreCase(String type);
    List<Recipe> findByNutritionKcalLessThanEqual(int maxKcal);
    
    List<Recipe> findByrNameContainingIgnoreCaseOrIngredientsContainingIgnoreCase(String nameQuery, String ingredientsQuery);
}