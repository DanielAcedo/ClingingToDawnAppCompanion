package com.danielacedo.clingingtodawn.model;

import com.danielacedo.clingingtodawn.R;

import java.util.Comparator;

/**
 * Created by Daniel on 12/11/2016.
 */

/**
 * Represents a note found in the game
 */
public class Note {
    private String title;
    private String content;
    private int backgroundImage;

    public static Comparator<Note> compareAlphabeticallyAscendent = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public static Comparator<Note> compareAlphabeticallyDescendent = new Comparator<Note>() {
        @Override
        public int compare(Note o1, Note o2) {
            return -1 * o1.getTitle().compareTo(o2.getTitle());
        }
    };

    public Note(String title, String content){
        this.title = title;
        this.content = content;
        this.backgroundImage = R.drawable.notedefaultbackground;
    }

    public Note(String title, String content, int backgroundImage){
        this(title, content);
        this.backgroundImage = backgroundImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(int backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

}
