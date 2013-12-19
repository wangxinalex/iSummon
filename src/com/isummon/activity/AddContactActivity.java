package com.isummon.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.isummon.R;
import com.isummon.model.Contact;
import com.isummon.model.UserModel;
import com.isummon.net.NetHelper;
import com.isummon.widget.ContactAdapter;

import java.sql.Array;
import java.util.Arrays;

/**
 * Created by horzwxy on 12/19/13.
 */
public class AddContactActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_contact);
    }

    public void doSearch(View v) {
        EditText input = (EditText) findViewById(R.id.search_contact_name_input);
        String username = input.getText().toString();
        if("".equals(username)) {
            Toast.makeText(this, R.string.error_input_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            UserModel user = NetHelper.findUserByName(username);
            Contact contact = new Contact(user.getUserId(),
                    user.getUserName(),
                    user.getAvatar());
            ContactAdapter adapter = new ContactAdapter(this, Arrays.asList(contact));
            ListView listView = (ListView) findViewById(R.id.contact_search_result);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
        }
    }

    private void showConfirmDialog(Contact contact) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.add_contact_confirm_1)
        + contact.getTargetName() + getString(R.string.add_contact_confirm_2));
        builder.setPositiveButton(R.string.add_contact_confirm_position,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.setNegativeButton(R.string.add_contact_confirm_negative,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nothing
                    }
                });
        builder.create().show();
    }
}
