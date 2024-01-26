
package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.DTO.RecipeDetailDTO;
import com.example.DTO.RecipeSummaryDTO;

import com.example.dao.RecipeDAO;

import com.example.model.Recipe;
import com.example.model.Review;

import com.example.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/recipi")
public class RecipeController {
	
	@Autowired
	RecipeDAO rDAO;
	
	
	
	@Autowired
	RecipeService rService;
	
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	
	@GetMapping("/recipes")
    public ResponseEntity<List<RecipeSummaryDTO>> getAllRecipeSummaries() {
        List<Recipe> recipes = rDAO.findAll();
        List<RecipeSummaryDTO> recipeSummaries = rService.mapRecipesTosummaryDTOs(recipes);

        return new ResponseEntity<>(recipeSummaries, HttpStatus.OK);
    }	
	
	
	@PostMapping("/addrecipe")
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        // Ensure the reviews list is not null
        if (recipe.getReviews() == null) {
            recipe.setReviews(new ArrayList<>()); // Set to an empty list if null
        }
        

        Recipe createdRecipe = rDAO.save(recipe);
        return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
    }

	
	@GetMapping("/recipes/{id}/details")
	public ResponseEntity<RecipeDetailDTO> getRecipeDetailsById(@PathVariable String id) {
	    Optional<Recipe> recipeOptional = rDAO.findById(id);

	    if (recipeOptional.isPresent()) {
	        Recipe recipe = recipeOptional.get();
	        RecipeDetailDTO recipeDetail = rService.mapRecipesToDetailsDTO(recipe);
	        return new ResponseEntity<>(recipeDetail, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/recipes/{id}/details/reviews")
    public ResponseEntity<List<Review>> getReviewsForRecipe(@PathVariable String id) {
        Optional<Recipe> recipeOptional = rDAO.findById(id);

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            List<Review> reviews = recipe.getReviews();
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	/*
	@PostMapping("/recipes/{id}/details/reviews/addreview")
	public ResponseEntity<List<Review>> addReviewToRecipe(
	        @PathVariable String id,
	        @RequestBody Review review) {
	    Query query = new Query(Criteria.where("_id").is(id));
	    Update update = new Update().addToSet("reviews", review);

	    // Use the update operation to add the new review to the existing recipe
	    mongoTemplate.updateFirst(query, update, Recipe.class);

	    // Retrieve the updated recipe
	    Recipe updatedRecipe = rDAO.findById(id).orElse(null);

	    if (updatedRecipe != null) {
	        // Return the list of reviews associated with the updated recipe
	        List<Review> reviewList = updatedRecipe.getReviews();
	        return new ResponseEntity<>(reviewList, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	*/
	
	@PostMapping("/recipes/{id}/details/reviews/addreview")
	public ResponseEntity<Review> addReviewToRecipe(
	        @PathVariable String id,
	        @RequestBody Review review) {
	    Query query = new Query(Criteria.where("_id").is(id));
	    Update update = new Update().addToSet("reviews", review);

	    // Use the update operation to add the new review to the existing recipe
	    mongoTemplate.updateFirst(query, update, Recipe.class);

	    // Return the posted review
	    return new ResponseEntity<>(review, HttpStatus.OK);
	}

	
	@GetMapping("/recipes/type/{recipeType}")
	public ResponseEntity<List<RecipeSummaryDTO>> getRecipesByType(@PathVariable String recipeType) {
	    List<Recipe> recipesByType = rDAO.findByTypeIgnoreCase(recipeType);

	    List<RecipeSummaryDTO> recipeSummaries = rService.mapRecipesTosummaryDTOs(recipesByType);

	    if (!recipeSummaries.isEmpty()) {
	        return new ResponseEntity<>(recipeSummaries, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/recipes/kcal/{maxKcal}")
	public ResponseEntity<List<RecipeSummaryDTO>> getRecipesByMaxKcal(@PathVariable int maxKcal) {
	    List<Recipe> recipesByMaxKcal = rDAO.findByNutritionKcalLessThanEqual(maxKcal);

	    List<RecipeSummaryDTO> recipeSummaries = rService.mapRecipesTosummaryDTOs(recipesByMaxKcal);

	    if (!recipeSummaries.isEmpty()) {
	        return new ResponseEntity<>(recipeSummaries, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/recipes/{query}")
	public ResponseEntity<List<RecipeSummaryDTO>> searchRecipes(@PathVariable String query) {
	    List<Recipe> recipes = rDAO.findByrNameContainingIgnoreCaseOrIngredientsContainingIgnoreCase(query, query);

	    List<RecipeSummaryDTO> recipeSummaries = rService.mapRecipesTosummaryDTOs(recipes);

	    if (!recipeSummaries.isEmpty()) {
	        return new ResponseEntity<>(recipeSummaries, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

}
