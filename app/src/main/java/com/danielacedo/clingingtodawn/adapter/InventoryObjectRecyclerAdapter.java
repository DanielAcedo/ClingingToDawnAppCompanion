package com.danielacedo.clingingtodawn.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.danielacedo.clingingtodawn.ItemTouchHelperCallback;
import com.danielacedo.clingingtodawn.R;
import com.danielacedo.clingingtodawn.exceptions.NoRecipeWithSuchIngredientsException;
import com.danielacedo.clingingtodawn.exceptions.RecipeIngredientsNotFullfilledException;
import com.danielacedo.clingingtodawn.model.InventoryObject;
import com.danielacedo.clingingtodawn.model.ItemRecipe;
import com.danielacedo.clingingtodawn.repository.InventoryObjectRepository;
import com.danielacedo.clingingtodawn.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 03/11/2016.
 */

public class InventoryObjectRecyclerAdapter extends RecyclerView.Adapter<InventoryObjectRecyclerAdapter.InventoryObjectHolder> implements ItemTouchHelperCallback.ItemTouchHelperAdapter {

    public static final int MODE_COMBINE = 1;
    public static final int MODE_MOVE = 2;

    private int mode = MODE_COMBINE;

    private List<InventoryObject> inventoryObjectList;
    private InventoryObjectCallback callback;
    private Context context;

    private boolean startedDragging = false;

    private int selectedPos = -1;

    private int fromPos = -1;
    private int targetPos = -1;

    //Interface for callback OnClick method to be implemented in the view
    public interface InventoryObjectCallback{
        void onClick(InventoryObject inventoryObject, InventoryObjectHolder holder);
        void onMoveEnd(int fromPos, int targetPos);
    }

    public InventoryObjectRecyclerAdapter(Context context, InventoryObjectCallback listener){
        this.context = context;
        this.inventoryObjectList = new ArrayList<InventoryObject>();
        this.inventoryObjectList.addAll(InventoryObjectRepository.getInventoryObjects());
        this.callback = listener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        recyclerView.setOnTouchListener(new RecyclerView.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Avoid interrupting dragging animations using notifyDataSetChanged()
                //We track when the drag is happening and once we release the touch, notifyDataSetChanged()
                //will be called
                if(startedDragging && (motionEvent.getActionMasked()) == MotionEvent.ACTION_UP) {
                    if (fromPos != -1 && targetPos != -1) {
                        startedDragging = false;

                        switch (mode){
                            case MODE_MOVE:
                                exchangePositions(fromPos, targetPos);
                                fromPos = -1;
                                targetPos = -1;

                                callback.onMoveEnd(fromPos, targetPos);

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        notifyDataSetChanged();
                                    }
                                }, 300);

                                break;
                            case MODE_COMBINE:
                                InventoryObject ingredient1 = getItem(fromPos);
                                InventoryObject ingredient2 = getItem(targetPos);

                                if (!combineItems(fromPos, targetPos, ingredient1, ingredient2)) {
                                    notifyDataSetChanged();
                                }
                                break;
                        }

                        return true;
                    }

                }

                return false;
            }
        });
    }

    public void removeItem(InventoryObject o){
        inventoryObjectList.remove(o);
        notifyDataSetChanged();

        saveToRepository();
    }

    private boolean combineItems(int fromPos, int targetPos, InventoryObject ingredient1, InventoryObject ingredient2) {
        try {
            if (!ingredient1.isCombinable()) {
                Toast.makeText(context, String.format(context.getString(R.string.inventory_combine_not_combinable), ingredient1.getName()), Toast.LENGTH_SHORT).show();
                return false;
            }

            if (!ingredient2.isCombinable()) {
                Toast.makeText(context, String.format(context.getString(R.string.inventory_combine_not_combinable), ingredient1.getName()), Toast.LENGTH_SHORT).show();
                return false;
            }

            ItemRecipe recipe = RecipeRepository.getInstance().findRecipe(ingredient1, ingredient2);
            InventoryObject newObject = recipe.cookRecipe(ingredient1, ingredient2);
            ingredient1.setQuantity(ingredient1.getQuantity() - recipe.getIngredient1().getQuantity());
            ingredient2.setQuantity(ingredient2.getQuantity() - recipe.getIngredient2().getQuantity());


            int contains = contains(newObject);

            if (contains != -1) {
                getItem(contains).setQuantity(getItem(contains).getQuantity() + 1);
            } else {
                inventoryObjectList.add(targetPos,newObject);
            }

            if (ingredient1.getQuantity() <= 0) {
                removeItem(ingredient1);
            }

            if (ingredient2.getQuantity() <= 0) {
                removeItem(ingredient2);
            }

            Toast.makeText(context, "Created "+newObject.getName()+" using "+ingredient1.getName()+" and "+ingredient2.getName(), Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();

        } catch (NoRecipeWithSuchIngredientsException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        } catch (RecipeIngredientsNotFullfilledException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public void onBindViewHolder(final InventoryObjectHolder holder, final int position) {
        holder.txv_inventoryObjectName.setText(inventoryObjectList.get(position).getName());

        if(inventoryObjectList.get(position).isStackable()){
            holder.txv_inventoryObjectQuantity.setText("x"+String.valueOf(inventoryObjectList.get(position).getQuantity()));
        }else{
            holder.txv_inventoryObjectQuantity.setText("");
        }

        if(position == selectedPos){
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.inventoryObjectBackground_selected));
        }else{
            holder.cardview.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }

        holder.imv_inventoryObjectImage.setImageResource(inventoryObjectList.get(position).getImage());
    }

    public void selectItem(InventoryObjectHolder holder, boolean select){
        selectedPos = holder.getLayoutPosition();
        notifyDataSetChanged();
    }

    public int contains(InventoryObject o){
        for (int i = 0; i < inventoryObjectList.size(); i++) {
            InventoryObject tmp = inventoryObjectList.get(i);

            if(tmp.getId() == o.getId()){
                return i;
            }
        }

        return -1;
    }

    public int getMode(){
        return mode;
    }

    public void setMode(int mode){
        this.mode = mode;
    }

    @Override
    public InventoryObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventoryobject_layout, parent, false);

        final InventoryObjectHolder holder = new InventoryObjectHolder(item);

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPos = holder.getLayoutPosition();

                callback.onClick(inventoryObjectList.get(holder.getLayoutPosition()), holder);

                notifyDataSetChanged();
            }
        });

        return holder;
    }

    public InventoryObject getItem(int pos){
        return inventoryObjectList.get(pos);
    }

    public InventoryObject getSelectedItem(){
        if(selectedPos!=-1){
            return getItem(selectedPos);
        }

        return null;
    }

    public int getSelectedPos(){
        return selectedPos;
    }

    public void setSelectedPos(int pos){
        selectedPos = pos;
        notifyDataSetChanged();

        saveToRepository();
    }

    private void saveToRepository(){
        InventoryObjectRepository.saveRepository(inventoryObjectList);
    }

    @Override
    public int getItemCount() {
        return inventoryObjectList.size();
    }

    public List<InventoryObject> getAll(){
        return inventoryObjectList;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //We are dragging now
        startedDragging = true;

        fromPos = fromPosition;
        targetPos = toPosition;

        return true;
    }

    @Override
    public void onItemMoved(int fromPosition, int toPosition, int animationDuration) {

    }

    private void exchangePositions(int fromPosition, int toPosition){
        InventoryObject from = inventoryObjectList.get(fromPosition);
        InventoryObject to = inventoryObjectList.get(toPosition);

        if(fromPosition == selectedPos){
            selectedPos = toPosition;
        }else if(toPosition == selectedPos){
            selectedPos = fromPosition;
        }

        inventoryObjectList.set(toPosition, from);
        inventoryObjectList.set(fromPosition, to);

        //Simulate the position exchange animation as notifyItemMoved makes an insert and resort animation
        notifyItemMoved(fromPosition, toPosition);
        if(fromPosition < toPosition){
            notifyItemMoved(toPosition-1, fromPosition);
        }else {
            notifyItemMoved(toPosition + 1, fromPosition);

        }

        saveToRepository();
    }

    @Override
    public void onItemDismiss(int position) {

    }

    public static class InventoryObjectHolder extends RecyclerView.ViewHolder{
        CardView cardview;
        ImageView imv_inventoryObjectImage;
        TextView txv_inventoryObjectName;
        TextView txv_inventoryObjectQuantity;

        boolean isSelected;

        public InventoryObjectHolder(View v) {
            super(v);
            cardview = (CardView)v.findViewById(R.id.card_view);
            imv_inventoryObjectImage = (ImageView)v.findViewById(R.id.imv_inventoryObjectImage);
            txv_inventoryObjectName = (TextView)v.findViewById(R.id.txv_inventoryObjectName);
            txv_inventoryObjectQuantity = (TextView)v.findViewById(R.id.txv_inventoryObjectQuantity);
            isSelected = false;
        }

        public void setCardBackground(int color){
            cardview.setCardBackgroundColor(color);
        }

        public boolean getSelected(){
            return isSelected;
        }

        public void setSelected(boolean value){
            isSelected = value;
        }

    }


}
