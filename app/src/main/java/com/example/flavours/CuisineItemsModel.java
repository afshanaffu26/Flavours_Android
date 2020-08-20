package com.example.flavours;

public class CuisineItemsModel {
    String name;
    String image;
    String price;
    String desc;
    String ingredients;

    public CuisineItemsModel() {
    }

    public CuisineItemsModel(String name, String image, String price, String desc, String ingredients) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.desc = desc;
        this.ingredients = ingredients;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
