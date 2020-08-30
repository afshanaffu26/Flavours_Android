package com.example.flavours;

public class CuisinesModel {
    String name;
    String image;

    public CuisinesModel() {
    }

    /**
     * Constructor to initialize name, image of product
     * @param name Cuisine name
     * @param image Cuisine image
     */
    public CuisinesModel(String name, String image) {
        this.name = name;
        this.image = image;
    }

    /**
     * Getter method to get name of cuisine
     * @return Name of cuisine
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method to set the name for a cuisine
     * @param name Name to be set to a cuisine.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method to get image of cuisine
     * @return Image of cuisine
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter method to set the image for a cuisine
     * @param image Image to be set to a cuisine .
     */
    public void setImage(String image) {
        this.image = image;
    }
}
