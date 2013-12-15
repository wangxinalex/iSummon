package com.isummon.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.isummon.R;


public class AddActivity extends Activity {

    public static final String ADDRESS_NAME = "address_name";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    static final int GET_ADDRESS = 876;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // remove the action bar
        // 'title bar' and 'action bar' refer to the same widget
        requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_add);

        // Only set bg in layout file is not enough: a dark bg still exists.
        getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);

        Intent intent = getIntent();
        String addressString = intent.getStringExtra( ADDRESS_NAME );
        EditText et = (EditText) findViewById(R.id.actPlace);
        if(addressString != null) {
            et.setText(addressString);
        }
        else {
            et.setHint(R.string.act_place_prompt);
            et.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAddressPicker();
                }
            });
        }
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GET_ADDRESS) {
            if(resultCode == RESULT_OK) {
                ((EditText)findViewById(R.id.actPlace)).setText(data.getStringExtra(ADDRESS_NAME));
            }
            else {
                // on error
            }
        }
    }

    private void showAddressPicker() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.address_choose);
        builder.setItems(R.array.address_input_source, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(which == 1) {
                    onChooseMap();
                }
                else {
                    onChooseSearch();
                }
            }
        });
        builder.create().show();
    }

    public void onChooseMap() {
        startActivityForResult(
                new Intent(this, PickMapAddressActivity.class),
                GET_ADDRESS);
    }

    public void onChooseSearch() {
        startActivityForResult(
                new Intent(this, SearchAddressActivity.class),
                GET_ADDRESS
        );
    }
}
