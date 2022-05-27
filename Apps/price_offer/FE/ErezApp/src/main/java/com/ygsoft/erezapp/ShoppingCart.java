package com.ygsoft.erezapp;

import java.util.List;
import java.util.ArrayList;
import com.ygsoft.erezapp.data.Item;



public class ShoppingCart {

    private final List<Item> itemsInCart = new ArrayList<>();

    public ShoppingCart(){}


    public void add(Item item) {
        itemsInCart.add(item);
    }



    public void print() {
        for (Item item : itemsInCart) {
            System.out.println(item);
        }
    }



    public List<Item> getItemsInCart() {
        return itemsInCart;
    }
}

