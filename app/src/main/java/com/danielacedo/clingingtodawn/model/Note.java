package com.danielacedo.clingingtodawn.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.danielacedo.clingingtodawn.R;

import java.util.Comparator;

/**
 * Created by Daniel on 12/11/2016.
 */

/**
 * Represents a note found in the game
 */
public class Note implements Parcelable {
    private int _id;
    private String title;
    private String content;
    private int backgroundImage;

    public static final int DEFAULT_IMAGE = R.drawable.note_default_background;

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

    public Note(){}

    public Note(int _id, String title, String content){
        this._id = _id;
        this.title = title;
        this.content = content;
        this.backgroundImage = R.drawable.notedefaultbackground;
    }

    public Note(String title, String content){
        this.title = title;
        this.content = content;
        this.backgroundImage = R.drawable.notedefaultbackground;
    }

    public Note(String title, String content, int backgroundImage){
        this(title, content);
        this.backgroundImage = backgroundImage;
    }

    public Note(int _id, String title, String content, int backgroundImage){
        this(_id, title, content);
        this.backgroundImage = backgroundImage;
    }

    protected Note(Parcel in) {
        _id = in.readInt();
        title = in.readString();
        content = in.readString();
        backgroundImage = in.readInt();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(backgroundImage);
    }
}
