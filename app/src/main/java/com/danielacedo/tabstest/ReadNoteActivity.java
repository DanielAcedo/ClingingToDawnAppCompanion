package com.danielacedo.tabstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

public class ReadNoteActivity extends AppCompatActivity {

    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String BACKGROUND = "background";

    TextView txv_title, txv_content;
    ScrollView scv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);

        txv_title = (TextView)findViewById(R.id.txv_readNote_title);
        txv_content = (TextView)findViewById(R.id.txv_readNote_content);
        scv_content = (ScrollView)findViewById(R.id.scv_content);

        getIntentData();

    }

    private void getIntentData(){
        txv_title.setText(getIntent().getStringExtra(TITLE));
        txv_content.setText(getIntent().getStringExtra(CONTENT));

        int backgroundId = getIntent().getIntExtra(BACKGROUND, R.drawable.note_default_background);
        scv_content.setBackground(getResources().getDrawable(backgroundId));
    }
}
