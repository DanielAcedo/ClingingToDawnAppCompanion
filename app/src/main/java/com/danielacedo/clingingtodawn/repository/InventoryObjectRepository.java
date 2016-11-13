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
    private static List<InventoryObject> inventoryObjectList;

    public static List<InventoryObject> getInventoryObjects(){
        if(inventoryObjectList == null){
            inventoryObjectList = new ArrayList<InventoryObject>();
            initializeInventoryObjects();
        }

        return inventoryObjectList;
    }

    private static void initializeInventoryObjects(){
        inventoryObjectList.add(new InventoryObject("Green Herb", "An herb that grows wild in this region", R.drawable.greenherb, true, 2));
        inventoryObjectList.add(new InventoryObject("Red Herb", "An herb that grows wild in this region", R.drawable.redherb, true, 2));
        inventoryObjectList.add(new InventoryObject("Battery", "With this, you can operate the elevator.", R.drawable.battery, false));
        inventoryObjectList.add(new InventoryObject("Old Key", "An old key of the mansion. Looks like you might be able to open most simply designed door locks", R.drawable.oldkey, false));
        inventoryObjectList.add(new InventoryObject("Blue Gemstone", "It's beautifully cut and polished to a mirror-like surface.", R.drawable.bluegemstone, false));
        inventoryObjectList.add(new InventoryObject("Missing Music", "It's titled, \"Moonlight Sonata\". A section of the music is missing, leaving only the beginning and the end of the music.", R.drawable.missingmusic, false));
        inventoryObjectList.add(new InventoryObject("Lighter", "It's a brass lighter coated with gold. Words are carved on it. \"Don't play with fire! Love Jessica\".", R.drawable.lighter, false));
        inventoryObjectList.add(new InventoryObject("Unprinted Book", "It's a book with a red cover. Nothing is printed on any of the pages. Is there a special way to use it?", R.drawable.unprintedbook, false));
        inventoryObjectList.add(new InventoryObject("Broken Shotgun", "It's broken and can't be fired. Maybe there's another use for this...", R.drawable.brokenshotgun, false));
        inventoryObjectList.add(new InventoryObject("Mixed Herbs (G+R)", "A mixture of red and green herbs.", R.drawable.mixedherbsgr, true, 1));
    }
}
