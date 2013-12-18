package com.isummon.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.isummon.R;
import com.isummon.view.ISummonMapView;

/**
 * Created by horzwxy on 12/15/13.
 */
public class PickMapAddressActivity extends Activity {

    private ISummonMapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_addr_map);

        mMapView = (ISummonMapView) findViewById(R.id.bmapsView);

        mMapView.setLongTouchAvailable(false);
        mMapView.setAddressPickedListener(new AddressPickedListener() {
            @Override
            public void onAddressPicked(double longitude, double latitude) {
                showAddressConfirmDialog(longitude, latitude);
            }
        });
    }

    private void showAddressConfirmDialog(final double longitude, final double latitude) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.pick_addr_from_map_dialog_title);
        View dialogContent = getLayoutInflater().inflate(R.layout.dialog_input_addr_name, null);
        final EditText nameEditor = (EditText) dialogContent.findViewById(R.id.addr_name_editor);
        builder.setView(dialogContent);
        builder.setPositiveButton(R.string.input_positive,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onAddressNameInput(nameEditor.getText().toString(),
                                longitude,
                                latitude);
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

    private void onAddressNameInput(String name, double longitude, double latitude) {
        if(name != null && !name.equals("")) {
            onReturnAddressResult(name, longitude, latitude);
        }
        else {
            Toast.makeText(this, R.string.input_empty_hint, Toast.LENGTH_SHORT).show();
        }
    }

    private void onReturnAddressResult(String name, double longitude, double latitude) {
        Intent intent = new Intent();
        intent.putExtra(AddActivity.ADDRESS_NAME, name );
        intent.putExtra(AddActivity.LONGITUDE, longitude);
        intent.putExtra(AddActivity.LATITUDE, latitude);
        setResult(RESULT_OK, intent);
        finish();
    }

    public interface AddressPickedListener {
        public void onAddressPicked(double longitude, double latitude);
    }

    @Override
    protected void onDestroy() {
        mMapView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
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
