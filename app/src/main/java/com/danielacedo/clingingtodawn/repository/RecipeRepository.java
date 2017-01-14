package com.danielacedo.clingingtodawn.repository;

import android.content.ClipData;

import com.danielacedo.clingingtodawn.exceptions.NoRecipeWithSuchIngredientsException;
import com.danielacedo.clingingtodawn.model.InventoryObject;
import com.danielacedo.clingingtodawn.model.ItemRecipe;
import com.danielacedo.clingingtodawn.model.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 14/01/2017.
 */

public class RecipeRepository {
    private static List<ItemRecipe> recipeList;
    private static RecipeRepository repository;


    private RecipeRepository(){
            recipeList = new ArrayList<ItemRecipe>();
            initializeRecipes();
    }

    public static RecipeRepository getInstance(){
        if(repository == null){
            repository = new RecipeRepository();
        }

        return repository;
    }

    private void initializeRecipes(){
        ItemRecipe recipe = null;

        RecipeIngredient ingredient1 = new RecipeIngredient(1, InventoryObjectRepository.findReferenceObject(0));
        RecipeIngredient ingredient2 = new RecipeIngredient(1, InventoryObjectRepository.findReferenceObject(1));

        recipe = new ItemRecipe(ingredient1, ingredient2, InventoryObjectRepository.findReferenceObject(9));

        recipeList.add(recipe);
    }

    public ItemRecipe findRecipe(InventoryObject ingredient1, InventoryObject ingredient2) throws NoRecipeWithSuchIngredientsException {

        for (ItemRecipe i : recipeList) {
            if(i.getIngredient1().getObject().getId() == ingredient1.getId()
                    || i.getIngredient1().getObject().getId() == ingredient2.getId()
                    || i.getIngredient2().getObject().getId() == ingredient1.getId()
                    || i.getIngredient2().getObject().getId() == ingredient2.getId()) {

                return i;
            }
        }

        throw new NoRecipeWithSuchIngredientsException("No recipe with those ingredients");
    }


}
