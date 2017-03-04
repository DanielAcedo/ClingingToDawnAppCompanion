package com.danielacedo.clingingtodawn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.danielacedo.clingingtodawn.adapter.NoteListAdapter;
import com.danielacedo.clingingtodawn.db.NoteDatabaseManager;

/**
 * Created by Daniel on 12/11/2016.
 */

/**
 * Fragment displaying a list of notes
 */
public class NoteListFragment extends Fragment {

    private ListView lv_noteList;
    private FloatingActionButton fab_addNote;
    private NoteListAdapter adapter;

    interface NoteListCallback{
        void showManageNotes(Bundle bundle);
    }

    private NoteListCallback callback;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            callback = (NoteListCallback)activity;
        }catch (ClassCastException e){
            throw new RuntimeException("Must implement NoteListCallback");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.notelist_fragment_layout, container, false);

        fab_addNote = (FloatingActionButton)v.findViewById(R.id.fab_addNote);
        fab_addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.showManageNotes(null);
            }
        });

        lv_noteList = (ListView)v.findViewById(R.id.lv_noteList);
        lv_noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openReadNoteActivity(position);
            }
        });

        adapter = new NoteListAdapter(container.getContext());
        lv_noteList.setAdapter(adapter);

        registerForContextMenu(lv_noteList);

        return v;
    }


    private void openReadNoteActivity(int position){
        Intent intent = new Intent(getContext(), ReadNoteActivity.class);
        intent.putExtra(ReadNoteActivity.TITLE, adapter.getItem(position).getTitle());
        intent.putExtra(ReadNoteActivity.CONTENT, adapter.getItem(position).getContent());
        intent.putExtra(ReadNoteActivity.BACKGROUND, adapter.getItem(position).getBackgroundImage());
        getContext().startActivity(intent);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getActivity().getMenuInflater();

        if (v.getId() == R.id.lv_noteList)
            inflater.inflate(R.menu.ctx_menu_note, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.ctx_action_remove:
                NoteDatabaseManager.deleteNote(adapter.getItem(menuInfo.position));
                adapter.refreshSource();
                break;

            case R.id.ctx_action_edit:
                Bundle bundle = new Bundle();
                bundle.putParcelable(ManageNoteFragment.KEY_EDIT_NOTE, adapter.getItem(menuInfo.position));
                callback.showManageNotes(bundle);
        }

        return true;
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
