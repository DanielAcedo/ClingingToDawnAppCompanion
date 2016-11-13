package com.danielacedo.tabstest;

import android.graphics.Region;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danielacedo.tabstest.adapter.InventoryObjectRecyclerAdapter;
import com.danielacedo.tabstest.model.InventoryObject;

/**
 * Created by Daniel on 3/11/16.
 */

/**
 * Fragment to be displayed as tab content
 * @author Daniel Acedo Calderón
 */
public class InventoryFragment extends Fragment {

    RecyclerView rcv_inventoryList;
    InventoryObjectRecyclerAdapter adapter;
    TextView txv_InventoryDescription;
    private final int SPAN_NUMBER = 3; //Number of elements per row

    //RecyclerView selected element
    InventoryObjectRecyclerAdapter.InventoryObjectHolder selectedInventoryObject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate from layout
        View v = inflater.inflate(R.layout.inventory_fragment_layout, container, false);

        txv_InventoryDescription = (TextView) v.findViewById(R.id.txv_inventoryDescription);

        //RecyclerView
        rcv_inventoryList = (RecyclerView)v.findViewById(R.id.rcv_inventoryList);
        rcv_inventoryList.setLayoutManager(new GridLayoutManager(container.getContext(), SPAN_NUMBER));

        //Set Adapter
        adapter = new InventoryObjectRecyclerAdapter(container.getContext(), new InventoryObjectRecyclerAdapter.InventoryObjectOnClick() {
            @Override
            public void onClick(InventoryObject inventoryObject, InventoryObjectRecyclerAdapter.InventoryObjectHolder holder) { //OnClick method to be executed for each recyclerview item
                //region OnClickAdapter
                txv_InventoryDescription.setText(inventoryObject.getDescription()); //Display the object's description in its TextView

                if(!holder.getSelected()){

                    //If there was a selected item before we deselect it and change its background color back to normal
                    if (selectedInventoryObject !=  null){
                        selectedInventoryObject.setSelected(false);
                        selectedInventoryObject.itemView.setBackgroundColor(getResources().getColor(R.color.tab_content_background_dark));
                    }

                    //Flag the item as selected and change its background color
                    selectedInventoryObject = holder;
                    holder.setSelected(true);
                    holder.itemView.setBackgroundColor(getResources().getColor(R.color.inventoryObjectBackground_selected));
                    holder.itemView.setBackgroundColor(getResources().getColor(R.color.inventoryObjectBackground_selected));
                }

                //endregion
            }
        });
        rcv_inventoryList.setAdapter(adapter);

        return v;
    }
}
