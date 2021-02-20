package com.ygsoft.erezapp.data;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;






public class Items {

    private final List<Item> itemsList = new ArrayList<>();


    public Items() {}



    public List<Item> getItemsList() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            Reader reader = new FileReader("config/items.json");

            JsonElement jeAll = gson.fromJson(reader, JsonElement.class);

            JsonObject joAll = jeAll.getAsJsonObject();

            Set<String> categories = joAll.keySet();

            for (String category : categories) {

                Category currentCategory = new Category(category);

                JsonElement jeCategory = joAll.get(category);

                JsonObject  joCategory = jeCategory.getAsJsonObject();

                Set<String> ids = joCategory.keySet();

                for (String id : ids) {

                    Make  make  = new Make(joCategory.get(id).getAsJsonObject().get("make").getAsString());
                    Model model = new Model(joCategory.get(id).getAsJsonObject().get("model").getAsString());
                    int   price = joCategory.get(id).getAsJsonObject().get("price").getAsInt();

                    // Set an 'Item' object out of it.
                    Item currentItem = new Item(id, currentCategory, make, model, price, 0);
                    itemsList.add(currentItem);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return itemsList;
    }



    public List<Make> getMakesFromCategory(String category) {

        List<Make> list = new ArrayList<>();
        List<String> names = new ArrayList<>();

        for (Item item : itemsList) {

            if (item.getCategory().getName().equals(category)) {

                Make make = item.getMake();

                if ( ! names.contains(make.getName())) {
                    names.add(make.getName());
                }
            }
        }

        // Sort the names list
        Collections.sort(names);

        for (String s : names) {
            list.add(new Make(s));
        }

        return list;
    }



    public List<Model> getModelFromMake(String make) {

        List<Model> list   = new ArrayList<>();
        List<String> names = new ArrayList<>();

        for (Item item : itemsList) {

            if (item.getMake().getName().equals(make)) {

                Model model = item.getModel();

                if ( ! names.contains(model.getName())) {
                    names.add(model.getName());
                }
            }
        }

        // Sort the list of names
        Collections.sort(names);

        for (String s : names) {
            list.add(new Model(s));
        }

        return list;
    }



    public int getPrice(String make, String model) {

        int price = 0;

        for (Item item : itemsList) {

            if (item.getMake().getName().equals(make) && item.getModel().getName().equals(model)) {
                price = item.getPrice();
                break;
            }
        }
        return price;
    }



    public String getIdFromItemProperties(Item item) {

        String id = null;

        for (Item i : itemsList) {
            if (item.getCategory().getName().equals(i.getCategory().getName())) {
                if (item.getMake().getName().equals(i.getMake().getName())) {
                    if (item.getModel().getName().equals(i.getModel().getName())) {
                        id = i.getId();
                        break;
                    }
                }
            }
        }

        return id;
    }
}


