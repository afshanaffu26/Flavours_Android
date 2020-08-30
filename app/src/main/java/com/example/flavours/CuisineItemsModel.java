package com.example.flavours;

/**
 * Model file for cuisine products
 */
public class CuisineItemsModel {
    String name;
    String image;
    String price;
    String desc;
    String ingredients;
    String quantity;

    public CuisineItemsModel() {
    }

    /**
     * Constructor to initialize name, image, price, desciption and ingredients of product
     * @param name Cuisine item name
     * @param image Cuisine item image
     * @param price Cuisine item price
     * @param desc Cuisine item description
     * @param ingredients Cuisine item Ingredients
     */
    public CuisineItemsModel(String name, String image, String price, String desc, String ingredients) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.desc = desc;
        this.ingredients = ingredients;
    }

    /**
     * Constructor to initialize name, image, price, desciption, ingredients and quantity of product
     * @param name Cuisine item name
     * @param image Cuisine item image
     * @param price Cuisine item price
     * @param desc Cuisine item description
     * @param ingredients Cuisine item ingredients
     * @param quantity Cuisine item quantity
     */
    public CuisineItemsModel(String name, String image, String price, String desc, String ingredients, String quantity) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.desc = desc;
        this.ingredients = ingredients;
        this.quantity = quantity;
    }

    /**
     * Getter method to get quantity of cuisine item
     * @return Quantity of cuisine item
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Setter method to set the quantity for a cuisine item
     * @param quantity Quantity to be set to a cuisine item.
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter method to get description of cuisine item
     * @return Description of cuisine item
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method to set the description for a cuisine item
     * @param desc Description to be set to a cuisine item.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Getter method to get ingredients of cuisine item
     * @return Ingredients of cuisine item
     */
    public String getIngredients() {
        return ingredients;
    }

    /**
     * Setter method to set the ingredients for a cuisine item
     * @param ingredients Ingredients to be set to a cuisine item.
     */
    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Getter method to get name of cuisine item
     * @return Name of cuisine item
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method to set the name for a cuisine item
     * @param name Name to be set to a cuisine item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method to get image of cuisine item
     * @return Image of cuisine item
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter method to set the image for a cuisine item
     * @param image Image to be set to a cuisine item.
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Getter method to get price of cuisine item
     * @return Price of cuisine item
     */
    public String getPrice() {
        return price;
    }

    /**
     * Setter method to set the price for a cuisine item
     * @param price Price to be set to a cuisine item.
     */
    public void setPrice(String price) {
        this.price = price;
    }
}
