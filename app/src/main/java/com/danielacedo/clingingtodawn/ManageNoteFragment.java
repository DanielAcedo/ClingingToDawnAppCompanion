package com.danielacedo.clingingtodawn;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.danielacedo.clingingtodawn.db.NoteDatabaseManager;
import com.danielacedo.clingingtodawn.model.Note;

public class ManageNoteFragment extends Fragment {
    public static final String KEY_EDIT_NOTE = "editnote";
    public static final String TAG = "ManageNoteFragment";
    private ManageNoteCallback mListener;

    private EditText edt_title;
    private EditText edt_description;
    private Button btn_acceptNote;

    private boolean isEditing = false;
    private Note editNote;

    public ManageNoteFragment() {

    }


    public static ManageNoteFragment newInstance(Bundle bundle) {
        ManageNoteFragment fragment = new ManageNoteFragment();

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_manage_note, container, false);

        edt_title = (EditText)v.findViewById(R.id.edt_NoteTitle);
        edt_description = (EditText)v.findViewById(R.id.edt_NoteDescription);

        btn_acceptNote = (Button)v.findViewById(R.id.btn_AcceptNote);
        btn_acceptNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();

                if (isEditing){
                    editNote();
                }else{
                    addNote();
                }
            }
        });


        checkEditMode();

        return v;
    }

    /**
     * Checks if fragment was initialized in edit mode
     */
    private void checkEditMode(){
        if(getArguments() != null){
            Note note = getArguments().getParcelable(KEY_EDIT_NOTE);

            if(note != null) {
                edt_title.setText(note.getTitle());
                edt_description.setText(note.getContent());
                btn_acceptNote.setText(getResources().getString(R.string.btn_EditNote));

                editNote = note;
                isEditing = true;
            }
        }
    }

    /**
     * Adds note to the database
     */
    public void addNote(){
        String title = edt_title.getText().toString();
        String description = edt_description.getText().toString();

        if(title.isEmpty() || description.isEmpty()){
            Toast.makeText(getContext(), getResources().getString(R.string.err_mn_fieldsEmpty), Toast.LENGTH_SHORT).show();
        }else{
            Note note = new Note(title, description);

            NoteDatabaseManager.insertNote(note);

            //Show notification
            NotificationManager nm = (NotificationManager)getContext().getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
            Notification notification = builder.setContentTitle("Nota añadida")
                    .setContentText("Añadida nota '"+note.getTitle()+"'")
                    .setSmallIcon(android.R.drawable.ic_notification_overlay)
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .build();

            nm.notify(null, 1, notification);

            mListener.openNoteList();
        }
    }

    public void editNote(){

        String title = edt_title.getText().toString();
        String description = edt_description.getText().toString();

        if(title.isEmpty() || description.isEmpty()){
            Toast.makeText(getContext(), getResources().getString(R.string.err_mn_fieldsEmpty), Toast.LENGTH_SHORT).show();
        }else{
            editNote.setTitle(title);
            editNote.setContent(description);
            NoteDatabaseManager.updateNote(editNote);
            mListener.openNoteList();
        }
    }

    /**
     * Hides keyboard when exiting the fragment
     */
    private void hideKeyboard(){
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ManageNoteCallback) {
            mListener = (ManageNoteCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ManageNoteCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //Clear the menu and load only the main one
        menu.clear();

        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public interface ManageNoteCallback {

        void openNoteList();
    }
}
