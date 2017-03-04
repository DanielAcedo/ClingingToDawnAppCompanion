package com.danielacedo.clingingtodawn;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.danielacedo.clingingtodawn.adapter.MainViewPagerAdapter;
import com.danielacedo.clingingtodawn.db.DatabaseHelper;


public class MainControlActivity extends AppCompatActivity implements NoteListFragment.NoteListCallback, ManageNoteFragment.ManageNoteCallback {

    private TabLayout tablayout;
    private Toolbar toolbar;
    private ViewPager viewpager;
    private MainViewPagerAdapter adapter;


    public static String CALL_DISCONNECT = "disconnect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_control_tablayout);

        DatabaseHelper.getInstance().getWritableDatabase().close();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tablayout = (TabLayout) findViewById(R.id.tablayout);
        tablayout.addTab(tablayout.newTab().setText(R.string.tab_inventory_title));
        tablayout.addTab(tablayout.newTab().setText(R.string.tab_notes_title));

        viewpager = (ViewPager)findViewById(R.id.viewpager);
        adapter = new MainViewPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(adapter);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tablayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(ManageNoteFragment.TAG);

        if(fragment != null){
           FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();

            tablayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showManageNotes(Bundle bundle) {
        tablayout.setVisibility(View.GONE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_list, ManageNoteFragment.newInstance(bundle), ManageNoteFragment.TAG);
        transaction.addToBackStack(ManageNoteFragment.TAG);
        transaction.commit();
    }

    @Override
    public void openNoteList() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(ManageNoteFragment.TAG);

        if(fragment != null){
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();

            viewpager.setAdapter(adapter);
            viewpager.setCurrentItem(1);
            tablayout.setVisibility(View.VISIBLE);

        }
    }

    private View getTabIndicator(Context context, String title){
        View v = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);

        TextView txv_tab_title = (TextView)v.findViewById(R.id.txv_tab_title);
        txv_tab_title.setText(title);

        return v;
    }
}
