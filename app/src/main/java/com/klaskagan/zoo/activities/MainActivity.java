package com.klaskagan.zoo.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.klaskagan.zoo.R;
import com.klaskagan.zoo.events.DrawerSectionItemClickedEvent;
import com.klaskagan.zoo.fragments.ExhibitsListFragment;
import com.klaskagan.zoo.fragments.GalleryFragment;
import com.klaskagan.zoo.fragments.ZooMapFragment;
import com.klaskagan.zoo.utils.EventBus;
import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private String mCurrentFragmentTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_opened, R.string
                .drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.drawer_opened);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(R.string.drawer_closed);
            }
        };

        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        displayInitialFragment();
    }

    private void displayInitialFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, ExhibitsListFragment.getInstance()).commit();
        mCurrentFragmentTitle = getString(R.string.section_exhibits);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getInstance().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getInstance().unregister(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onDrawerSectionItemClickedEvent(DrawerSectionItemClickedEvent event) {
        mDrawerLayout.closeDrawers();
        if (event == null || TextUtils.isEmpty(event.getSection()) || event.getSection().equalsIgnoreCase
                (mCurrentFragmentTitle)) {
            return;
        }
        Toast.makeText(this, "Main Activity: section clicked: " + event.getSection(), Toast.LENGTH_SHORT).show();

        if (event.getSection().equalsIgnoreCase(getString(R.string.section_map))) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, ZooMapFragment.getInstance()).commit();
        } else if (event.getSection().equalsIgnoreCase(getString(R.string.section_gallery))) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, GalleryFragment.getInstance()).commit();
        } else if (event.getSection().equalsIgnoreCase(getString(R.string.section_exhibits))) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, ExhibitsListFragment.getInstance()).commit();
        } else {
            return;
        }
        mCurrentFragmentTitle = event.getSection();

    }
}
