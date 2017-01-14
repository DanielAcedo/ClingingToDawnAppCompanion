package com.danielacedo.clingingtodawn;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.danielacedo.clingingtodawn.adapter.InventoryObjectRecyclerAdapter;
import com.danielacedo.clingingtodawn.model.InventoryObject;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

/**
 * Created by Daniel on 3/11/16.
 */

/**
 * Fragment to be displayed as tab content
 * @author Daniel Acedo Calderón
 */
public class InventoryFragment extends Fragment {

    private final int SPAN_NUMBER_PORTRAIT = 3; //Number of elements per row on portrait
    private final int SPAN_NUMBER_LANDSCAPE = 4; //Number of elements per row on landscape

    private final String ITEM_DESCRIPTION = "description";
    private final String SELECTED_ITEM = "selected";
    private final String ADAPTER_MODE = "adaptermode";

    private RecyclerView rcv_inventoryList;
    private InventoryObjectRecyclerAdapter adapter;
    private ItemTouchHelper.Callback touchHelper;

    private TextView txv_InventoryDescription;

    //RecyclerView selected element
    private InventoryObjectRecyclerAdapter.InventoryObjectHolder selectedInventoryObject;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate from layout
        View v = inflater.inflate(R.layout.inventory_fragment_layout, container, false);

        txv_InventoryDescription = (TextView) v.findViewById(R.id.txv_inventoryDescription);

        //RecyclerView
        rcv_inventoryList = (RecyclerView)v.findViewById(R.id.rcv_inventoryList);

        //Change the number of columns based on the screen orientation
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            rcv_inventoryList.setLayoutManager(new GridLayoutManager(container.getContext(), SPAN_NUMBER_PORTRAIT));
        }else{
            rcv_inventoryList.setLayoutManager(new GridLayoutManager(container.getContext(), SPAN_NUMBER_LANDSCAPE));
        }

        //Set Adapter
        adapter = new InventoryObjectRecyclerAdapter(container.getContext(), new InventoryObjectRecyclerAdapter.InventoryObjectCallback() {
            @Override
            public void onClick(InventoryObject inventoryObject, InventoryObjectRecyclerAdapter.InventoryObjectHolder holder) { //OnClick method to be executed for each recyclerview item
                //region OnClickAdapter
                txv_InventoryDescription.setText(inventoryObject.getDescription()); //Display the object's description in its TextView

                //endregion
            }

            @Override
            public void onMoveEnd(int fromPos, int targetPos) {

            }
        });

        ItemTouchHelperCallback touchCallback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(touchCallback);
        touchHelper.attachToRecyclerView(rcv_inventoryList);
        rcv_inventoryList.setAdapter(adapter);

        Toast.makeText(getContext(), R.string.inventory_drag_advice, Toast.LENGTH_SHORT).show();

        return v;
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(txv_InventoryDescription!=null){
            outState.putString(ITEM_DESCRIPTION, txv_InventoryDescription.getText().toString());
        }


        outState.putInt(SELECTED_ITEM, adapter.getSelectedPos());


        outState.putInt(ADAPTER_MODE, adapter.getMode());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            txv_InventoryDescription.setText(savedInstanceState.getString(ITEM_DESCRIPTION));

            int selectedPos = savedInstanceState.getInt(SELECTED_ITEM);
            if(selectedPos != -1){
                adapter.setSelectedPos(selectedPos);
            }
            adapter.setMode(savedInstanceState.getInt(ADAPTER_MODE));
        }

        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_inventory, menu);

        MenuItem changeMode = menu.findItem(R.id.item_changeMode);

        if(adapter.getMode() == InventoryObjectRecyclerAdapter.MODE_COMBINE){
            changeMode.setIcon(getResources().getDrawable(R.drawable.ic_action_combine));
        }else{
            changeMode.setIcon(getResources().getDrawable(R.drawable.ic_action_move));
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item_changeMode){
            if(adapter.getMode() == InventoryObjectRecyclerAdapter.MODE_COMBINE){
                adapter.setMode(InventoryObjectRecyclerAdapter.MODE_MOVE);
                Toast.makeText(getContext(), String.format(getResources().getString(R.string.inventory_changed_mode), getResources().getString(R.string.inventory_MOVE_MODE)), Toast.LENGTH_SHORT).show();
                item.setIcon(getResources().getDrawable(R.drawable.ic_action_move));
            }else{
                adapter.setMode(InventoryObjectRecyclerAdapter.MODE_COMBINE);
                Toast.makeText(getContext(), String.format(getResources().getString(R.string.inventory_changed_mode), getResources().getString(R.string.inventory_COMBINE_MODE)), Toast.LENGTH_SHORT).show();
                item.setIcon(getResources().getDrawable(R.drawable.ic_action_combine));
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
