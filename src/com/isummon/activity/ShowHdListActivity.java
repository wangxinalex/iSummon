package com.isummon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.a.a.c.c;
import com.isummon.R;
import com.isummon.model.HDActivity;
import com.isummon.model.HDProperty;

/**
 * Created by horzwxy on 12/18/13.
 */
public class ShowHdListActivity extends Activity {

    public static final String HDACTIVITY = "hdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_act);

        Intent intent = getIntent();
        HDActivity hdActivity = (HDActivity) intent.getSerializableExtra(HDACTIVITY);

        setEditText(R.id.actName, R.string.act_name_prompt, hdActivity.getHdName() );
        setEditText(R.id.actPlace, R.string.act_place_prompt, hdActivity.getHdAddress());
        setEditText(R.id.add_act_start_date, R.string.act_start_date_prompt, hdActivity.getHdStartTime());
        setEditText(R.id.add_act_start_time, R.string.act_start_time_prompt, hdActivity.getHdStartTime());
        setEditText(R.id.add_act_end_date, R.string.act_end_date_prompt, hdActivity.getHdEndTime());
        setEditText(R.id.add_act_end_time, R.string.act_end_time_prompt, hdActivity.getHdEndTime());
        setEditText(R.id.actContent, R.string.act_content_prompt, hdActivity.getHdDesc());
        if(hdActivity.getHdProperty() == HDProperty.OTHER) {
            setEditText(R.id.act_property, R.string.act_property_prompt, HDProperty.OTHER.getChn());
        }
        else {
            EditText editText = (EditText) findViewById(R.id.act_property);
            editText.setText(hdActivity.getHdProperty().getChn());
        }

    }

    private void setEditText(int viewId, int hintStringId, String content) {
        EditText editText = (EditText) findViewById(viewId);
        editText.setEnabled(false);
        editText.setText(getString(hintStringId) + " : " + content);
    }
}
