package com.danielacedo.clingingtodawn;

import android.app.Application;
import android.content.Context;

/**
 * Created by Daniel on 03/11/2016.
 */

public class InventoryTabsApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext(){
        return context;
    }



}
