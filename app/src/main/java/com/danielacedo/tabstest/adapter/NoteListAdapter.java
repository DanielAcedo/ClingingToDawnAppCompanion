package com.danielacedo.tabstest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielacedo.tabstest.InventoryTabsApplication;
import com.danielacedo.tabstest.R;
import com.danielacedo.tabstest.model.InventoryObject;
import com.danielacedo.tabstest.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 03/11/2016.
 */

public class NoteListAdapter extends ArrayAdapter<Note> {

    private Context context;

    public NoteListAdapter(Context context){
        super(context, R.layout.note_layout, InventoryTabsApplication.getNoteList());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        NoteHolder holder;

        if(item == null){
            holder = new NoteHolder();
            item = LayoutInflater.from(getContext()).inflate(R.layout.note_layout, null);

            holder.txv_noteListTitle = (TextView)item.findViewById(R.id.txv_noteListTitle);

            item.setTag(holder);
        }else{
            holder = (NoteHolder)item.getTag();
        }

        holder.txv_noteListTitle.setText(getItem(position).getTitle());

        return item;
    }

    static class NoteHolder{
        TextView txv_noteListTitle;
    }
}
