package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.DTO.RecipeDetailDTO;
import com.example.DTO.RecipeSummaryDTO;
import com.example.model.Recipe;

@Service
public class RecipeService {

    public List<RecipeSummaryDTO> mapRecipesTosummaryDTOs(List<Recipe> recipes) {
        List<RecipeSummaryDTO> recipeSummaries = new ArrayList<>();

        for (Recipe recipe : recipes) {
            RecipeSummaryDTO recipeSummaryDTO = new RecipeSummaryDTO();
            recipeSummaryDTO.setId(recipe.getId());
            recipeSummaryDTO.setrName(recipe.getrName());
            recipeSummaryDTO.setImageURL(recipe.getImageURL());

            recipeSummaries.add(recipeSummaryDTO);
        }

        return recipeSummaries;
    }
    
    public RecipeDetailDTO mapRecipesToDetailsDTO(Recipe recipe) {
        
        RecipeDetailDTO recipeDetail = new RecipeDetailDTO();

        recipeDetail.setId(recipe.getId());
        recipeDetail.setrName(recipe.getrName());
        recipeDetail.setImageURL(recipe.getImageURL());
        recipeDetail.setIngredients(recipe.getIngredients());
        recipeDetail.setDescription(recipe.getDescription());
        recipeDetail.setType(recipe.getType());
        recipeDetail.setNutrition(recipe.getNutrition());

        return recipeDetail;
    }
}
