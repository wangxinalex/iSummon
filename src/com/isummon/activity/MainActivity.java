package com.isummon.activity;

import android.os.Bundle;

import com.baidu.mapapi.BMapManager;
import com.isummon.R;
import com.isummon.view.ISummonMapView;


public class MainActivity extends FullScreenActivity {
    private BMapManager mBMapMan;
    private ISummonMapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBMapMan = ((TestApplication) this.getApplication()).getBMapManager();
        addContentView(R.layout.activity_main);
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
}
