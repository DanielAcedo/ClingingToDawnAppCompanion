package com.danielacedo.tabstest;

import android.app.Application;

import com.danielacedo.tabstest.model.InventoryObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 03/11/2016.
 */

public class InventoryTabsApplication extends Application {
    private static List<InventoryObject> inventoryObjectList;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInventoryList();
    }

    private void initializeInventoryList(){
        inventoryObjectList = new ArrayList<InventoryObject>();

        inventoryObjectList.add(new InventoryObject("Green Herb", "An herb that grows wild in this region", 2, R.drawable.greenherb));
        inventoryObjectList.add(new InventoryObject("Red Herb", "An herb that grows wild in this region", 2, R.drawable.redherb));
        inventoryObjectList.add(new InventoryObject("Battery", "With this, you can operate the elevator.", 1, R.drawable.battery));
        inventoryObjectList.add(new InventoryObject("Old Key", "An old key of the mansion. Looks like you might be able to open most simply designed door locks.", 1, R.drawable.oldkey));
        inventoryObjectList.add(new InventoryObject("Blue Gemstone", "It's beautifully cut and polished to a mirror-like surface", 1, R.drawable.bluegemstone));
    }

    public static List<InventoryObject> getInventoryObjectList(){
        return inventoryObjectList;
    }
}
