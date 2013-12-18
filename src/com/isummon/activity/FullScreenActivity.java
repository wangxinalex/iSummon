package com.isummon.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.isummon.R;

/**
 * Created by horzwxy on 12/18/13.
 */
public class FullScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_frame);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
