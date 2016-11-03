package com.danielacedo.tabstest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danielacedo.tabstest.adapter.InventoryObjectRecyclerAdapter;

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
    private final int SPAN_NUMBER = 3; //Number of elements per row

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate from layout
        View v = inflater.inflate(R.layout.fragment_test, container, false);

        //RecyclerView
        rcv_inventoryList = (RecyclerView)v.findViewById(R.id.rcv_inventoryList);
        rcv_inventoryList.setLayoutManager(new GridLayoutManager(container.getContext(), SPAN_NUMBER));
        //Set Adapter
        adapter = new InventoryObjectRecyclerAdapter(container.getContext());
        rcv_inventoryList.setAdapter(adapter);

        return v;
    }
}
