package com.danielacedo.clingingtodawn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.danielacedo.clingingtodawn.adapter.NoteListAdapter;

/**
 * Created by Daniel on 12/11/2016.
 */

/**
 * Fragment displaying a list of notes
 */
public class NoteListFragment extends Fragment {

    private ListView lv_noteList;
    private NoteListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View v = inflater.inflate(R.layout.notelist_fragment_layout, container, false);

        lv_noteList = (ListView)v.findViewById(R.id.lv_noteList);

        adapter = new NoteListAdapter(container.getContext());
        lv_noteList.setAdapter(adapter);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_notelist, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.menu_notelist_sortAlphabetically){
            adapter.sortAlphabetically();
        }

        return super.onOptionsItemSelected(item);
    }
}
