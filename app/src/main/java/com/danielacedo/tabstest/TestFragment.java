package com.danielacedo.tabstest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Daniel on 03/11/2016.
 */

/**
 * Placeholder fragment that solely displays a text
 * @author Daniel Acedo Calderón
 */
public class TestFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.testfragment_layout, container, false);
        TextView txv_text = (TextView)v.findViewById(R.id.txv_testfragment_text);
        txv_text.setText(getTag());

        return v;
    }
}
