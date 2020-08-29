package com.example.flavours;

/**
 * Model file for cart
 */
public class CartModel {
    String name;
    String image;
    String price;
    String quantity;
    String desc;
    String ingredients;

    public CartModel() {
    }

    public CartModel(String name, String image, String price, String quantity) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Getter for name parameter
     * @return Product name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for  product name
     * @param name Product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for image
     * @return Product image
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter for product image
     * @param image Product image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Getter for Product price
     * @return Product price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Setter for Product price
     * @param price  Product price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Getter for Product Quantity
     * @return Product Quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Setter for Product price
     * @param quantity  Product price
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
