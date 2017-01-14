package com.danielacedo.clingingtodawn.exceptions;

/**
 * Created by Daniel on 14/01/2017.
 */

public class NoRecipeWithSuchIngredientsException extends Exception {
    public NoRecipeWithSuchIngredientsException(String message) {
        super(message);
    }
}
