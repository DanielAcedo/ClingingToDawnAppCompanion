package com.danielacedo.clingingtodawn.model;

/**
 * Created by Daniel on 03/11/2016.
 */

/**
 * Represents one object in the game's inventory
 */
public class InventoryObject {
    private int id;
    private String name;
    private String description;
    private int quantity;
    private int image;

    private boolean stackable;
    private boolean combinable;

    public InventoryObject(int id, String name, String description, int image, boolean combinable){
        this.name = name;
        this.description = description;
        this.image = image;
        this.stackable = false;
        this.combinable = combinable;
        this.id = id;
    }

    public InventoryObject(int id,String name, String description, int image, boolean combinable, int quantity){
        this(id,name, description, image, combinable);
        this.stackable = true;
        this.quantity = quantity;

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

    public boolean isStackable() {
        return stackable;
    }

    public boolean isCombinable() {
        return combinable;
    }

    public int getId() {
        return id;
    }

}
