package com.imransk.newsviews.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.imransk.newsviews.Fragment.AboutFragment;
import com.imransk.newsviews.Fragment.HomeFragment;
import com.imransk.newsviews.R;

public class MainActivity extends AppCompatActivity {
    private LinearLayout clk1, clk2, clk3, clk4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        clk1=(LinearLayout)findViewById(R.id.click1);
        clk2=(LinearLayout)findViewById(R.id.click2);
        clk3=(LinearLayout)findViewById(R.id.click3);
        clk4=(LinearLayout)findViewById(R.id.click4);
        Initializing();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    public void Initializing(){
        Fragment fragment = null;
        Class fragmentClass =  HomeFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }// Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.screen_area, fragment).commit();
    }

    public void ClickNavigation(View view){
        Fragment fragment = null;
        Class fragmentClass =  HomeFragment.class;

        switch (view.getId()){
            case R.id.click1:
                fragmentClass = HomeFragment.class;
                clk1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_clickb, null));
                clk2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk4.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                break;
            case R.id.click2:
                fragmentClass = AboutFragment.class;
                Toast.makeText(this, "Click 2", Toast.LENGTH_SHORT).show();
                clk1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_clickb, null));
                clk3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk4.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                break;
            case R.id.click3:
                Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
                clk1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_clickb, null));
                clk4.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                break;
            case R.id.click4:
                Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();
                clk1.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk2.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk3.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_click, null));
                clk4.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.button_clickb, null));
                break;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }// Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.screen_area, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
