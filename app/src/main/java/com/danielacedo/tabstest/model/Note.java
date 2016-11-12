package com.danielacedo.tabstest.model;

import com.danielacedo.tabstest.R;

/**
 * Created by Daniel on 12/11/2016.
 */

public class Note {
    private String title;
    private String content;
    private int backgroundImage;

    public Note(String title, String content){
        this.title = title;
        this.content = content;
        this.backgroundImage = R.drawable.note_background_default;
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
