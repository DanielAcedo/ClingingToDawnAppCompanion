package com.danielacedo.clingingtodawn.model;

import com.danielacedo.clingingtodawn.exceptions.RecipeIngredientsNotFullfilledException;

import java.util.List;

/**
 * Created by Daniel on 14/01/2017.
 */

public class ItemRecipe {
    private static final int FULFILL_1 = 1;
    private static final int FULFILL_2 = 2;
    private static final int FULFILL_BOTH = 3;
    private static final int FULFILL_NONE = 0;

    RecipeIngredient ingredient1;
    RecipeIngredient ingredient2;
    InventoryObject result;


    public ItemRecipe(RecipeIngredient ingredient1, RecipeIngredient ingredient2, InventoryObject result) {
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.result = result;
    }

    public RecipeIngredient getIngredient1(){
        return ingredient1;
    }

    public RecipeIngredient getIngredient2(){
        return ingredient2;
    }

    public InventoryObject cookRecipe(InventoryObject ingredient1, InventoryObject ingredient2) throws RecipeIngredientsNotFullfilledException {
        if(canCook(ingredient1, ingredient2)){
            return result;
        }else{
            throw new RecipeIngredientsNotFullfilledException("Ingredients don't meet the recipe requeriments");
        }
    }

    public boolean canCook(InventoryObject o, InventoryObject o2){
        int result = checkIngredient(o) + checkIngredient(o2);

        if(result == FULFILL_BOTH){
            return true;
        }else{
            return false;
        }
    }

    private int checkIngredient(InventoryObject o){
        int result = FULFILL_NONE;

        if(ingredient1.getObject().getId() == o.getId() && o.getQuantity() >= ingredient1.getQuantity()){
            result = FULFILL_1;
        }

        if(ingredient2.getObject().getId() == o.getId() && o.getQuantity() >= ingredient2.getQuantity()){
            result = FULFILL_2;
        }

        return result;
    }

}
