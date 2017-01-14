package com.danielacedo.clingingtodawn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.danielacedo.clingingtodawn.InventoryFragment;
import com.danielacedo.clingingtodawn.NoteListFragment;

/**
 * Created by Daniel on 14/01/2017.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private int fragmentCount = 0;

    public MainViewPagerAdapter(FragmentManager fragmentManager, int fragmentCount){
        super(fragmentManager);
        this.fragmentCount = fragmentCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position){
            case 0:
                fragment = new InventoryFragment();
                break;
            case 1:
                fragment = new NoteListFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentCount;
    }
}
