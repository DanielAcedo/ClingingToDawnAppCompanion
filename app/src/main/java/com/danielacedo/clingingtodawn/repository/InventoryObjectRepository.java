package com.danielacedo.clingingtodawn.repository;

import com.danielacedo.clingingtodawn.R;
import com.danielacedo.clingingtodawn.model.InventoryObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 13/11/2016.
 */

/**
 * Repository for holding a single instance of a list of InventoryObjects
 */
public class InventoryObjectRepository {
    private static List<InventoryObject> referenceObjectList;
    private static List<InventoryObject> inventoryObjectList;

    public static List<InventoryObject> getInventoryObjects(){
        if(inventoryObjectList == null){
            referenceObjectList = new ArrayList<>();
            inventoryObjectList = new ArrayList<InventoryObject>();

            initializeReferenceObjects();
            initializeInventoryObjects();
        }

        return inventoryObjectList;
    }

    private static void initializeInventoryObjects(){
        for (int i = 0; i < 9; i++){
            inventoryObjectList.add(referenceObjectList.get(i));
        }
    }

    private static void initializeReferenceObjects(){
        referenceObjectList.add(new InventoryObject(0,"Green Herb", "An herb that grows wild in this region", R.drawable.greenherb, true, 2));
        referenceObjectList.add(new InventoryObject(1,"Red Herb", "An herb that grows wild in this region", R.drawable.redherb, true, 2));
        referenceObjectList.add(new InventoryObject(2,"Battery", "With this, you can operate the elevator.", R.drawable.battery, false));
        referenceObjectList.add(new InventoryObject(3,"Old Key", "An old key of the mansion. Looks like you might be able to open most simply designed door locks", R.drawable.oldkey, false));
        referenceObjectList.add(new InventoryObject(4,"Blue Gemstone", "It's beautifully cut and polished to a mirror-like surface.", R.drawable.bluegemstone, false));
        referenceObjectList.add(new InventoryObject(5,"Missing Music", "It's titled, \"Moonlight Sonata\". A section of the music is missing, leaving only the beginning and the end of the music.", R.drawable.missingmusic, false));
        referenceObjectList.add(new InventoryObject(6,"Lighter", "It's a brass lighter coated with gold. Words are carved on it. \"Don't play with fire! Love Jessica\".", R.drawable.lighter, false));
        referenceObjectList.add(new InventoryObject(7,"Unprinted Book", "It's a book with a red cover. Nothing is printed on any of the pages. Is there a special way to use it?", R.drawable.unprintedbook, false));
        referenceObjectList.add(new InventoryObject(8,"Broken Shotgun", "It's broken and can't be fired. Maybe there's another use for this...", R.drawable.brokenshotgun, false));
        referenceObjectList.add(new InventoryObject(9,"Mixed Herbs (G+R)", "A mixture of red and green herbs.", R.drawable.mixedherbsgr, false, 1));
    }

    public static void saveRepository(List<InventoryObject> objects){
        inventoryObjectList = new ArrayList<>(objects);
    }

    public static InventoryObject findReferenceObject(int id){
        for (InventoryObject o: referenceObjectList) {
            if(o.getId() == id)
                return o;
        }

        return null;
    }
}
