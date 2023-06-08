package com.arsenii.android.lab7;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class FirstTab extends Activity implements DialogInterface.OnClickListener {
    private static final int DIALOG_EXIT = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_tab);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(R.string.exit);
            adb.setMessage(R.string.save_data);
            adb.setIcon(android.R.drawable.ic_dialog_info);
            adb.setPositiveButton(R.string.yes, this);
            adb.setNegativeButton(R.string.no, this);
            adb.setNeutralButton(R.string.canсel, this);
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == DialogInterface.BUTTON_POSITIVE) {
            saveData();
        }
        if(which == DialogInterface.BUTTON_NEGATIVE || which == DialogInterface.BUTTON_POSITIVE) {
            finish();
        }
    }

    private void saveData() {
        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
    }
}
