package com.danielacedo.tabstest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.danielacedo.tabstest.adapter.NoteListAdapter;

import java.util.List;

/**
 * Created by Daniel on 12/11/2016.
 */

public class NoteListFragment extends Fragment {

    private ListView lv_noteList;
    private NoteListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.notelist_fragment_layout, container, false);

        lv_noteList = (ListView)v.findViewById(R.id.lv_noteList);

        adapter = new NoteListAdapter(container.getContext());
        lv_noteList.setAdapter(adapter);

        return v;
    }
}
