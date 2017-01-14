package com.danielacedo.clingingtodawn.model;

/**
 * Created by Daniel on 14/01/2017.
 */

public class RecipeIngredient {
    private InventoryObject object;
    private int quantity;

    public RecipeIngredient(int quantity, InventoryObject object) {
        this.quantity = quantity;
        this.object = object;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public InventoryObject getObject() {
        return object;
    }

    public void setObject(InventoryObject object) {
        this.object = object;
    }
}
