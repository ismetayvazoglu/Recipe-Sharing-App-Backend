package com.example.DTO;


public class RecipeSummaryDTO {

    private String id;
    private String rName;
    private String imageURL;
    
    
	public RecipeSummaryDTO() {
		// TODO Auto-generated constructor stub
	}

	public RecipeSummaryDTO(String id, String rName, String imageURL) {
		super();
		this.id = id;
		this.rName = rName;
		this.imageURL = imageURL;
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

	
	

    // Constructors, getters, and setters
    
    // ... other methods
}