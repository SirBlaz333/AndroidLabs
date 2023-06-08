package com.arsenii.android.lab7;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class SecondTab extends Activity {
    private static final String LOGS = "logs";
    private static final int MAX_COUNT = 100;
    private ProgressBar progressBar;
    private TextView textView;
    private CheckBox checkBox;
    private Handler handler;
    private int count;

    private final Runnable updateProgress = () -> progressBar.setProgress(count);

    private final Runnable showInfo = new Runnable() {
        @Override
        public void run() {
            Log.d(LOGS, "showInfo");
            String text = "Count = " + count;
            textView.setText(text);
            handler.postDelayed(showInfo, 1000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_tab);
        handler = new Handler();
        progressBar = findViewById(R.id.pbCount);
        progressBar.setMax(MAX_COUNT);
        progressBar.setProgress(0);
        textView = findViewById(R.id.tvInfo);
        checkBox = findViewById(R.id.chbInfo);

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                textView.setVisibility(View.VISIBLE);
                handler.post(showInfo);
            } else {
                textView.setVisibility(View.GONE);
                handler.removeCallbacks(showInfo);
            }
        });
        new Thread(() -> {
            try {
                for (count = 1; count < MAX_COUNT; count++) {
                    TimeUnit.MILLISECONDS.sleep(100);
                    handler.post(updateProgress);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
