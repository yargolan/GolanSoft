package com.ygsoft.erezapp.data;

public class Item {

    private String id;

    private final Make make;
    private final Model model;
    private final Category category;

    private final int price;
    private final int amount;



    public Item(String itemId, Category itemCategory, Make itemMake, Model itemModel, int itemPrice, int itemAmount) {
        this.id       = itemId;
        this.make     = itemMake;
        this.model    = itemModel;
        this.price    = itemPrice;
        this.amount   = itemAmount;
        this.category = itemCategory;
    }



    public void setId(String newId) {
        this.id = newId;
    }



    public String getId() {
        return this.id;
    }



    public Make getMake() {
        return make;
    }



    public Model getModel() {
        return model;
    }



    public Category getCategory() {
        return category;
    }



    public int getPrice() {
        return price;
    }



    public int getAmount() {
        return amount;
    }
}
