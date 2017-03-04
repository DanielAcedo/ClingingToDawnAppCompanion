package com.danielacedo.clingingtodawn.db;

import android.provider.BaseColumns;

import com.danielacedo.clingingtodawn.R;

/**
 * Created by Daniel on 04/03/2017.
 */

public class DatabaseContract {
    public static class NoteContract implements BaseColumns{
        public static final String TABLE_NAME = "Note";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMAGE = "image";

        public static final String SQL_CREATE = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL)",
                TABLE_NAME,
                BaseColumns._ID,
                COLUMN_TITLE,
                COLUMN_DESCRIPTION,
                COLUMN_IMAGE
        );

        public static final String SQL_INSERT = String.format(
          "INSERT INTO %s (%s,%s,%s) VALUES ('%s', '%s', %d), ('%s', '%s', %d)",
                TABLE_NAME, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_IMAGE,
                "El reloj no es lo que parece",
                "El reloj está maldito, ni se te ocurra manipular las manecillas o sufrirás las consecuencias",
                R.drawable.note_default_background,

                "Diario del cocinero",
                "Dia 1. \n No me puedo creer que esté trabajando aquí, es todo un honor a servir en esta casa. Me he traido las mejores tijeras de mi colección para dar mi mejor servicio, las dejaré siempre en el segundo cajón de la cocina",
                R.drawable.note_default_background
        );

        public static final String SQL_DELETE = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

        public static final String[] ALL_COLUMNS = {BaseColumns._ID, COLUMN_TITLE, COLUMN_DESCRIPTION, COLUMN_IMAGE};
    }
}
