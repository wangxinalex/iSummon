package com.isummon.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.isummon.R;
import com.isummon.model.ActListMode;
import com.isummon.model.HDType;
import com.isummon.net.FakeDataProvider;
import com.isummon.view.SimpleHdAdapter;

import java.util.Arrays;
import java.util.List;


public class ListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

        final Spinner submodeSpinner = (Spinner) findViewById(R.id.list_submode_selector);
        ArrayAdapter<HDType> submodeAdapter = new ArrayAdapter<HDType>(
                this,
                android.R.layout.simple_spinner_item,
                HDType.values() );
        submodeSpinner.setAdapter(submodeAdapter);
        submodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        submodeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HDType type = (HDType) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );

        Spinner modeSpinner = (Spinner) findViewById(R.id.list_mode_selector);
        final ArrayAdapter<ActListMode> modeAdapter = new ArrayAdapter<ActListMode>(
                this,
                android.R.layout.simple_spinner_item,
                ActListMode.values());
        modeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSpinner.setAdapter(modeAdapter);
        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                findViewById(R.id.list_search_content).setEnabled(false);
                findViewById(R.id.search_button).setEnabled(false);
                submodeSpinner.setEnabled(false);

                ActListMode mode = (ActListMode)parent.getItemAtPosition(position);
                switch (mode) {
                    case ALL:
                        break;
                    case ORIGIN:
                        findViewById(R.id.list_search_content).setEnabled(true);
                        findViewById(R.id.search_button).setEnabled(true);
                        break;
                    case TYPE:
                        submodeSpinner.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ListView listView = ((ListView)findViewById(R.id.list_content));
        listView.setAdapter(
                new SimpleHdAdapter(this, FakeDataProvider.getSimpleHDActivities()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, ShowHdDetailActivity.class);
                intent.putExtra(ShowHdDetailActivity.HDACTIVITY, FakeDataProvider.getHDById((int)id));
                startActivity(intent);
            }
        });


    }

    public void doSearch(View v) {

    }
}
