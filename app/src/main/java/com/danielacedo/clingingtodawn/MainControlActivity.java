package com.danielacedo.clingingtodawn;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainControlActivity extends AppCompatActivity {

    FragmentTabHost fth_main;

    public static String CALL_DISCONNECT = "disconnect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_control);

        fth_main = (FragmentTabHost)findViewById(R.id.fth_main);

        fth_main.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        //First tab
        View indicator = getTabIndicator(this, getResources().getString(R.string.tab_inventory_title));
        TabHost.TabSpec spec = fth_main.newTabSpec("Tab1").setIndicator(indicator);
        fth_main.addTab(spec, InventoryFragment.class, null);

        //Second tab
        indicator = getTabIndicator(this, getResources().getString(R.string.tab_notes_title));
        spec = fth_main.newTabSpec("Tab2").setIndicator(indicator);
        fth_main.addTab(spec, NoteListFragment.class, null);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inventory, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;

        switch(item.getItemId()){
            case R.id.menu_main_preferences:
                intent = new Intent(MainControlActivity.this, OptionSettings.class);
                startActivity(intent);
                break;
            case R.id.menu_main_about:
                intent = new Intent(MainControlActivity.this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_main_disconnect:
                intent = new Intent(MainControlActivity.this, Login_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setAction(CALL_DISCONNECT);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //No se puede pulsar
    }

    private View getTabIndicator(Context context, String title){
        View v = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);

        TextView txv_tab_title = (TextView)v.findViewById(R.id.txv_tab_title);
        txv_tab_title.setText(title);

        return v;
    }
}
