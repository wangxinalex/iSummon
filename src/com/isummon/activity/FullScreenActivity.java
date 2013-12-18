package com.isummon.activity;

import android.app.Activity;
import android.view.Menu;

import com.isummon.R;

/**
 * Created by horzwxy on 12/18/13.
 */
public class FullScreenActivity extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
