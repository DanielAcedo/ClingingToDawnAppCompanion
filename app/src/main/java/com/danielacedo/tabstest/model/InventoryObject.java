package com.danielacedo.tabstest.model;

/**
 * Created by Daniel on 03/11/2016.
 */

public class InventoryObject {
    private String name;
    private String description;
    private int quantity;
    private int image;

    public InventoryObject(String name, String description, int quantity, int image){
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
