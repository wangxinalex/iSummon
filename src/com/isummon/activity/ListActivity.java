package com.isummon.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.isummon.R;
import com.isummon.model.ActListMode;
import com.isummon.net.FakeDataProvider;
import com.isummon.view.SimpleHdAdapter;

import java.util.ArrayList;


public class ListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

        Spinner spinner = (Spinner) findViewById(R.id.list_mode_selector);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                ActListMode.getChns());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ((ListView)findViewById(R.id.list_content)).setAdapter(
                new SimpleHdAdapter(this, FakeDataProvider.getSimpleHDActivities()));

    }

}
