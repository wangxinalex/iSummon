package com.isummon.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.isummon.R;
import com.isummon.model.HDActivity;

import java.util.Calendar;


public class AddActivity extends Activity {

    static final String ADDRESS_NAME = "address_name";
    static final String LONGITUDE = "longitude";
    static final String LATITUDE = "latitude";
    static final int GET_ADDRESS = 876;

    private final static double DEFAULT_LATITUDE = 120;
    private final static double DEFAULT_LONGITUDE = 35;
    private final static String DEFAULT_ADDR_NAME = "第二教学楼";

    private HDActivity result;
    private Calendar hdStartDate;
    private Calendar hdStartTime;
    private Calendar hdEndDate;
    private Calendar hdEndTime;
    private Dialog typePicker;

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
        hdStartDate = hdEndDate = hdStartTime = hdEndTime = Calendar.getInstance();

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

        // the listeners can be set in XML, but... I want the XML to be shared by some other activities without these listeners
        findViewById(R.id.add_act_start_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(hdStartDate, R.id.add_act_start_date);
            }
        });
        findViewById(R.id.add_act_start_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(hdStartTime, R.id.add_act_start_time);
            }
        });
        findViewById(R.id.add_act_end_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(hdEndDate, R.id.add_act_end_date);
            }
        });
        findViewById(R.id.add_act_end_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker(hdEndTime, R.id.add_act_end_time);
            }
        });

        findViewById(R.id.act_type_content).setVisibility(View.INVISIBLE);

        findViewById(R.id.act_type_prompt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTypePicker();
            }
        });
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GET_ADDRESS) {
            if(resultCode == RESULT_OK) {
                ((EditText)findViewById(R.id.actPlace)).setText(data.getStringExtra(ADDRESS_NAME));
                result.setHdAddress(data.getStringExtra(ADDRESS_NAME));
                result.setLatitude(data.getDoubleExtra(LATITUDE, DEFAULT_LATITUDE));
                result.setLongitude(data.getDoubleExtra(LONGITUDE, DEFAULT_LONGITUDE));
            }
            else {
                // on error
            }
        }
    }

    private void showTypePicker() {
        typePicker = new Dialog(this);
        ListView listView = (ListView) getLayoutInflater().inflate(R.layout.dialog_pick_type, null);
        listView.setAdapter(new HDTypePickerAdapter(this));
        typePicker.setContentView(listView);
        typePicker.show();
    }

    private void showDatePicker(final Calendar date, final int layoutId) {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date.set(Calendar.YEAR, year);
                date.set( Calendar.MONTH, month );
                date.set( Calendar.DAY_OF_MONTH, day );
                ((EditText)findViewById(layoutId)).setText(getDateRepresentation(date));
            }
        };
        final Calendar currentDate = Calendar.getInstance();
        new DatePickerDialog( this,
                listener,
                currentDate.get(Calendar.YEAR),
                currentDate.get( Calendar.MONTH ),
                currentDate.get( Calendar.DAY_OF_MONTH ) ).show();
    }

    private void showTimePicker(final Calendar time, final int layoutId) {
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                time.set( Calendar.HOUR_OF_DAY, hour );
                time.set( Calendar.MINUTE, minute );
                ((EditText)findViewById(layoutId)).setText( getTimeRepresentation( time ) );
            }
        };
        Calendar currentTime = Calendar.getInstance();
        new TimePickerDialog( this,
                listener,
                currentTime.get( Calendar.HOUR_OF_DAY ),
                currentTime.get( Calendar.MINUTE ),
                true ).show();
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

    private String getDateRepresentation(Calendar date) {
        int actualMonth = date.get(Calendar.MONTH) + 1;
        String monthResp = actualMonth < 10 ? "0" + actualMonth : "" + actualMonth;
        int actualDay = date.get(Calendar.DAY_OF_MONTH);
        String dayResp = actualDay < 10 ? "0" + actualDay : actualDay + "";
        return date.get(Calendar.YEAR) + "年" + monthResp + "月" + dayResp + "日";
    }

    private String getTimeRepresentation(Calendar date) {
        String hourResp = date.get(Calendar.HOUR_OF_DAY) < 10 ? "0" + date.get(Calendar.HOUR_OF_DAY) : "" + date.get(Calendar.HOUR_OF_DAY);
        String minuteResp = date.get(Calendar.MINUTE) < 10 ? "0" + date.get(Calendar.MINUTE) : date.get(Calendar.MINUTE) + "";
        return hourResp + " : " + minuteResp;
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
        // fake return
//        ((EditText)findViewById(R.id.actPlace)).setText(DEFAULT_ADDR_NAME);
//        result.setHdAddress(DEFAULT_ADDR_NAME);
//        result.setLatitude(DEFAULT_LATITUDE);
//        result.setLongitude(DEFAULT_LONGITUDE);

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

    private class HDTypePickerAdapter extends ArrayAdapter<String> {

        public HDTypePickerAdapter(Context context) {
            super(context, R.layout.dialog_pick_act_type_item);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            TextView typeNameView = (TextView) convertView.findViewById(R.id.act_type_name);
            typeNameView.setText(getItem(position));
            ImageView typeImage = (ImageView) convertView.findViewById(R.id.act_type_image);
            typeImage.setImageResource(getTypeImageId(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    findViewById(R.id.act_type_prompt).setVisibility(View.INVISIBLE);
                    findViewById(R.id.act_type_content).setVisibility(View.VISIBLE);
                    ((ImageView)findViewById(R.id.act_type_image)).setImageResource(getTypeImageId(position));
                    ((TextView)findViewById(R.id.act_type_name)).setText(getItem(position));
                    typePicker.dismiss();
                }
            });
            return super.getView(position, convertView, parent);
        }

        private int getTypeImageId(int position) {
            switch (position) {
                case 0:
                    return R.drawable.sport;
                case 1:
                    return R.drawable.study;
                case 2:
                    return R.drawable.entertainment;
                case 3:
                    return R.drawable.dining;
                case 4:
                    return R.drawable.other;
            }
            return -1;
        }
    }
}
