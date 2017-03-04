package com.danielacedo.clingingtodawn.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.danielacedo.clingingtodawn.InventoryTabsApplication;

/**
 * Created by Daniel on 04/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String NAME = "clingingtodawn";
    private static final int VERSION = 2;
    private static volatile DatabaseHelper databaseHelper;


    private DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    public static DatabaseHelper getInstance(){
        if(databaseHelper == null){
            databaseHelper = new DatabaseHelper(InventoryTabsApplication.getContext());
        }

        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.NoteContract.SQL_CREATE);
        db.execSQL(DatabaseContract.NoteContract.SQL_INSERT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.NoteContract.SQL_DELETE);

        onCreate(db);
    }
}
