package com.danielacedo.tabstest;

import android.app.Application;

import com.danielacedo.tabstest.model.InventoryObject;
import com.danielacedo.tabstest.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 03/11/2016.
 */

public class InventoryTabsApplication extends Application {
    private static List<InventoryObject> inventoryObjectList;
    private static List<Note> noteList;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInventoryList();
        initializeNoteList();
    }

    private void initializeInventoryList(){
        inventoryObjectList = new ArrayList<InventoryObject>();

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

    private void initializeNoteList(){
        noteList = new ArrayList<Note>();

        noteList.add(new Note("El reloj no es lo que parece", "El reloj está maldito, ni se te ocurra manipular las manecillas o sufriras las consecuencias", R.drawable.note_default_background));
        noteList.add(new Note("Diario del cocinero", "Dia 1. \n No me puedo creer que esté trabajando aquí, es todo un honor a servir en esta casa. Me he traido las mejores tijeras de mi colección para dar mi mejor servicio, las dejaré siempre en el segundo cajón de la coina", R.drawable.note_default_background));
    }

    public static List<InventoryObject> getInventoryObjectList(){
        return inventoryObjectList;
    }

    public static List<Note> getNoteList(){
        return noteList;
    }
}
