package com.danielacedo.tabstest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielacedo.tabstest.InventoryTabsApplication;
import com.danielacedo.tabstest.R;
import com.danielacedo.tabstest.model.InventoryObject;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 03/11/2016.
 */

public class InventoryObjectRecyclerAdapter extends RecyclerView.Adapter<InventoryObjectRecyclerAdapter.InventoryObjectHolder> {

    private List<InventoryObject> inventoryObjectList;
    private Context context;

    public InventoryObjectRecyclerAdapter(Context context){
        this.context = context;
        this.inventoryObjectList = new ArrayList<InventoryObject>();
        this.inventoryObjectList.addAll(InventoryTabsApplication.getInventoryObjectList());
    }

    @Override
    public void onBindViewHolder(InventoryObjectHolder holder, int position) {
        holder.txv_inventoryObjectName.setText(inventoryObjectList.get(position).getName());
        holder.txv_inventoryObjectQuantity.setText("x"+String.valueOf(inventoryObjectList.get(position).getQuantity()));
        holder.imv_inventoryObjectImage.setImageResource(inventoryObjectList.get(position).getImage());
    }

    @Override
    public InventoryObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventoryobject_layout, parent, false);

        return new InventoryObjectHolder(item);
    }

    @Override
    public int getItemCount() {
        return inventoryObjectList.size();
    }

    public static class InventoryObjectHolder extends RecyclerView.ViewHolder{
        ImageView imv_inventoryObjectImage;
        TextView txv_inventoryObjectName;
        TextView txv_inventoryObjectQuantity;


        public InventoryObjectHolder(View v) {
            super(v);
            imv_inventoryObjectImage = (ImageView)v.findViewById(R.id.imv_inventoryObjectImage);
            txv_inventoryObjectName = (TextView)v.findViewById(R.id.txv_inventoryObjectName);
            txv_inventoryObjectQuantity = (TextView)v.findViewById(R.id.txv_inventoryObjectQuantity);
        }
    }


}
