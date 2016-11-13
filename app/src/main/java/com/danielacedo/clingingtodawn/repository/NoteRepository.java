package com.danielacedo.clingingtodawn.repository;

import com.danielacedo.clingingtodawn.R;
import com.danielacedo.clingingtodawn.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 13/11/2016.
 */

/**
 * Repository for holding a single instance of a list of Notes
 */
public class NoteRepository {
    private static List<Note> noteList;

    public static List<Note> getNotes(){
        if(noteList == null){
            noteList = new ArrayList<Note>();
            initializeNotes();
        }

        return noteList;
    }

    private static void initializeNotes(){
        noteList.add(new Note("El reloj no es lo que parece", "El reloj está maldito, ni se te ocurra manipular las manecillas o sufriás las consecuencias", R.drawable.note_default_background));
        noteList.add(new Note("Diario del cocinero", "Dia 1. \n No me puedo creer que esté trabajando aquí, es todo un honor a servir en esta casa. Me he traido las mejores tijeras de mi colección para dar mi mejor servicio, las dejaré siempre en el segundo cajón de la cocina", R.drawable.note_default_background));
    }
}
