package com.isummon.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

import com.isummon.R;


public class AddActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_add);

        getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);
	}
}
