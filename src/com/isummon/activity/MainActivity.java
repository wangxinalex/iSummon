package com.isummon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.baidu.mapapi.BMapManager;
import com.isummon.R;
import com.isummon.widget.ISummonMapView;


public class MainActivity extends Activity {
    private BMapManager mBMapMan;
    private ISummonMapView mMapView;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private LinearLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(R.string.app_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(R.string.fake_nickname);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mDrawer = (LinearLayout) findViewById(R.id.main_drawer);

        mBMapMan = ((TestApplication) this.getApplication()).getBMapManager();
        mMapView = (ISummonMapView) findViewById(R.id.bmapsView);
    }

    @Override
    protected void onDestroy() {
        mMapView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        if (mBMapMan != null) {
            mBMapMan.stop();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        // I found if you wants to respond the long-tap action, you must start the manager.
        // And if you don't want to respond, it's useless to do anything while the manager is started.
        if (mBMapMan != null) {
            mBMapMan.start();
        }
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mMapView.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawer);
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setVisible(!drawerOpen);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.menu_add_act:
                startActivity(new Intent(getApplicationContext(), AddActActivity.class));
                break;

            case R.id.menu_all_act:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ListActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_exit:
                // DBL!!
                android.os.Process.killProcess(android.os.Process.myPid());
        }

        return super.onOptionsItemSelected(item);
    }
}
