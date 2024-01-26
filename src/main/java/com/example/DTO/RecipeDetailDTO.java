package com.example.DTO;


import com.example.model.Nutrition;

public class RecipeDetailDTO {

    private String id;
    private String rName;
    private String imageURL;
    private String ingredients;
    private String description;
    private String type;
    private Nutrition nutrition;
    
	public RecipeDetailDTO() {
		// TODO Auto-generated constructor stub
	}

	public RecipeDetailDTO(String id, String rName, String image, String ingredients, String description,
			String type, Nutrition nutrition) {
		super();
		this.id = id;
		this.rName = rName;
		this.imageURL = image;
		this.ingredients = ingredients;
		this.description = description;
		this.type = type;
		this.nutrition = nutrition;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Nutrition getNutrition() {
		return nutrition;
	}

	public void setNutrition(Nutrition nutrition) {
		this.nutrition = nutrition;
	}



	

    
    // Constructors, getters, setters

    // ... other methods
}
