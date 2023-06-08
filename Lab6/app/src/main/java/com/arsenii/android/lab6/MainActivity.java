package com.arsenii.android.lab6;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends Activity {
    private static CustomTask customTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("qwe", "create MainActivity: " + this);

        TextView textView = findViewById(R.id.tv);
        customTask = (CustomTask) getLastNonConfigurationInstance();
        if (customTask == null) {
            customTask = new CustomTask();
            customTask.execute();
        }
        customTask.link(this, textView);
        Log.d("qwe", "create MyTask: " + customTask);
    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        customTask.unlink();
        return customTask;
    }
}