package com.arsenii.android.lab6;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class CustomTask extends AsyncTask<String, Integer, Void> {
    private MainActivity activity;
    private TextView textView;

    public void link(MainActivity activity, TextView textView){
        this.activity = activity;
        this.textView = textView;
    }

    public void unlink() {
        activity = null;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            for (int i = 1; i <= 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                publishProgress(i);
                Log.d("qwe", "i = " + i
                        + ", MyTask: " + this
                        + ", MainActivity: " + activity);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        textView.setText("i = " + values[0]);
    }
}
