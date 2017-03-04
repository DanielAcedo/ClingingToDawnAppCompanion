package com.danielacedo.clingingtodawn.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.danielacedo.clingingtodawn.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 04/03/2017.
 */

public class NoteDatabaseManager {

    public static List<Note> getNotes(){
        SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
        Cursor cursor = db.query(DatabaseContract.NoteContract.TABLE_NAME, DatabaseContract.NoteContract.ALL_COLUMNS, null, null, null, null, null);

        List<Note> notes = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {
                Note note = new Note();
                note.set_id(cursor.getInt(cursor.getColumnIndex(DatabaseContract.NoteContract._ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseContract.NoteContract.COLUMN_TITLE)));
                note.setContent(cursor.getString(cursor.getColumnIndex(DatabaseContract.NoteContract.COLUMN_DESCRIPTION)));
                note.setBackgroundImage(cursor.getInt(cursor.getColumnIndex(DatabaseContract.NoteContract.COLUMN_IMAGE)));

                notes.add(note);

            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return notes;
    }

    public static int deleteNote(Note note){
        int result = 0;

        SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
        result = db.delete(DatabaseContract.NoteContract.TABLE_NAME, DatabaseContract.NoteContract._ID+"="+note.get_id(), null);

        db.close();

        return result;
    }

    public static int updateNote(Note note){
        int result = 0;

        SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();

        result = db.update(DatabaseContract.NoteContract.TABLE_NAME, noteToContentValue(note),
                DatabaseContract.NoteContract._ID+"="+note.get_id(), null);

        db.close();

        return result;
    }

    public static boolean insertNote(Note note){
        long result = 0;

        SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();

        result = db.insert(DatabaseContract.NoteContract.TABLE_NAME, null, noteToContentValue(note));

        db.close();

        return (result != -1);
    }

    private static ContentValues noteToContentValue(Note note){
        ContentValues cv = new ContentValues();

        if(note.get_id()!=0){
            cv.put(DatabaseContract.NoteContract._ID, note.get_id());
        }

        cv.put(DatabaseContract.NoteContract.COLUMN_TITLE, note.getTitle());
        cv.put(DatabaseContract.NoteContract.COLUMN_DESCRIPTION, note.getContent());
        cv.put(DatabaseContract.NoteContract.COLUMN_IMAGE, note.getBackgroundImage());

        return cv;
    }

}
