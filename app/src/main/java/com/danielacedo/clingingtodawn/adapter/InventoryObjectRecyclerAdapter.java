package com.danielacedo.clingingtodawn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielacedo.clingingtodawn.R;
import com.danielacedo.clingingtodawn.model.InventoryObject;
import com.danielacedo.clingingtodawn.repository.InventoryObjectRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 03/11/2016.
 */

public class InventoryObjectRecyclerAdapter extends RecyclerView.Adapter<InventoryObjectRecyclerAdapter.InventoryObjectHolder> {

    private List<InventoryObject> inventoryObjectList;
    private InventoryObjectOnClick onClickListener;
    private Context context;

    //Interface for callback OnClick method to be implemented in the view
    public interface InventoryObjectOnClick{
        void onClick(InventoryObject inventoryObject, InventoryObjectHolder holder);
    }

    public InventoryObjectRecyclerAdapter(Context context, InventoryObjectOnClick listener){
        this.context = context;
        this.inventoryObjectList = new ArrayList<InventoryObject>();
        this.inventoryObjectList.addAll(InventoryObjectRepository.getInventoryObjects());
        this.onClickListener = listener;
    }

    @Override
    public void onBindViewHolder(final InventoryObjectHolder holder, final int position) {
        holder.txv_inventoryObjectName.setText(inventoryObjectList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(inventoryObjectList.get(position), holder);
            }
        });

        if(inventoryObjectList.get(position).isStackable()){
            holder.txv_inventoryObjectQuantity.setText("x"+String.valueOf(inventoryObjectList.get(position).getQuantity()));
        }else{
            holder.txv_inventoryObjectQuantity.setText("");
        }

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

    public List<InventoryObject> getAll(){
        return inventoryObjectList;
    }

    public static class InventoryObjectHolder extends RecyclerView.ViewHolder{
        ImageView imv_inventoryObjectImage;
        TextView txv_inventoryObjectName;
        TextView txv_inventoryObjectQuantity;

        boolean isSelected;

        public InventoryObjectHolder(View v) {
            super(v);
            imv_inventoryObjectImage = (ImageView)v.findViewById(R.id.imv_inventoryObjectImage);
            txv_inventoryObjectName = (TextView)v.findViewById(R.id.txv_inventoryObjectName);
            txv_inventoryObjectQuantity = (TextView)v.findViewById(R.id.txv_inventoryObjectQuantity);
            isSelected = false;
        }

        public boolean getSelected(){
            return isSelected;
        }

        public void setSelected(boolean value){
            isSelected = value;
        }

    }


}
