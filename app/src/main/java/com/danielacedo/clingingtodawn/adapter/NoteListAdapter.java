package com.danielacedo.clingingtodawn.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.danielacedo.clingingtodawn.R;
import com.danielacedo.clingingtodawn.ReadNoteActivity;
import com.danielacedo.clingingtodawn.db.NoteDatabaseManager;
import com.danielacedo.clingingtodawn.model.Note;
import com.danielacedo.clingingtodawn.repository.NoteRepository;

import java.util.Comparator;

/**
 * Created by Daniel on 03/11/2016.
 */

public class NoteListAdapter extends ArrayAdapter<Note> {

    private Context context;
    private boolean sortedAlphAscendent;

    public NoteListAdapter(Context context){
        super(context, R.layout.notelist_layout, NoteDatabaseManager.getNotes());
        sortedAlphAscendent = false;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View item = convertView;
        NoteHolder holder;

        if(item == null){
            holder = new NoteHolder();
            item = LayoutInflater.from(getContext()).inflate(R.layout.notelist_layout, null);

            holder.txv_noteListTitle = (TextView)item.findViewById(R.id.txv_noteListTitle);

            item.setTag(holder);
        }else{
            holder = (NoteHolder)item.getTag();
        }

        holder.txv_noteListTitle.setText(getItem(position).getTitle());

        return item;
    }

    public void refreshSource(){
        clear();
        addAll(NoteDatabaseManager.getNotes());
    }



    public void sortAlphabetically(){
        Comparator<Note> comparator = sortedAlphAscendent ? Note.compareAlphabeticallyDescendent : Note.compareAlphabeticallyAscendent;
        sortedAlphAscendent = !sortedAlphAscendent;
        sort(comparator);
        notifyDataSetChanged();
    }

    static class NoteHolder{
        TextView txv_noteListTitle;
    }
}
