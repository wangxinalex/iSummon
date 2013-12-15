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
import android.widget.Toast;

import com.isummon.R;
import com.isummon.model.HDActivity;


public class AddActivity extends Activity {

    static final String ADDRESS_NAME = "address_name";
    static final String LONGITUDE = "longitude";
    static final String LATITUDE = "latitude";
    static final int GET_ADDRESS = 876;

    private HDActivity result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // remove the action bar
        // 'title bar' and 'action bar' refer to the same widget
        requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_add);

        // Only set bg in layout file is not enough: a dark bg still exists.
        getWindow().setBackgroundDrawableResource(R.drawable.dialog_bg);

        result = new HDActivity();

        Intent intent = getIntent();
        double longitude = intent.getDoubleExtra( LONGITUDE, -200d );
        double latitude = intent.getDoubleExtra( LATITUDE, -200d );
        EditText et = (EditText) findViewById(R.id.actPlace);
        et.setHint(R.string.act_place_prompt);
        // if longitude is greater than -181, it must be a valid number
        if(longitude > -181) {
            et.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAddressNameEditor();
                }
            });
        }
        else {
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

    private void showAddressNameEditor() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pick_addr_from_map_title);
        View dialogContent = getLayoutInflater().inflate(R.layout.dialog_input_addr_name, null);
        final EditText nameEditor = (EditText) dialogContent.findViewById(R.id.addr_name_editor);
        builder.setView(dialogContent);
        builder.setPositiveButton(R.string.input_positive,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onAddressNameInput(nameEditor.getText().toString());
                        // no need to call dismiss
                    }
                });
        builder.setNegativeButton(R.string.input_negative,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // no need to call dismiss. System will help that.
                    }
                });
        builder.setCancelable(true);
        builder.create().show();
    }

    private void onAddressNameInput(String name) {
        if(name != null && !name.equals("")) {
            EditText et = (EditText) findViewById(R.id.actPlace);
            et.setText(name);
        }
        else {
            Toast.makeText(this, R.string.input_empty_hint, Toast.LENGTH_SHORT).show();
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

    private void onChooseMap() {
        startActivityForResult(
                new Intent(this, PickMapAddressActivity.class),
                GET_ADDRESS);
    }

    private void onChooseSearch() {
        Toast.makeText(this, R.string.search_not_available, Toast.LENGTH_SHORT).show();
//        startActivityForResult(
//                new Intent(this, SearchAddressActivity.class),
//                GET_ADDRESS
//        );
    }
}
