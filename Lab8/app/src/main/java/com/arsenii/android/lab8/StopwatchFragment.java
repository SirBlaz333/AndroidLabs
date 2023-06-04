package com.arsenii.android.lab8;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class StopwatchFragment extends Fragment implements View.OnClickListener {
    private final Map<Integer, Runnable> clickMap = Map.of(
            R.id.start_button, this::onClickStart,
            R.id.stop_button, this::onClickStop,
            R.id.reset_button, this::onClickReset);
    private int seconds = 0;
    private boolean isRunning;
    private boolean wasRunning;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        if (wasRunning) {
            isRunning = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.stopwatch_workout, container, false);
        runTimer(layout);
        Button startButton = layout.findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button stopButton = layout.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(this);
        Button resetButton = layout.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
        return layout;
    }

    @Override
    public void onPause() {
        super.onPause();
        wasRunning = isRunning;
        isRunning = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            isRunning = true;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", isRunning);
        outState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    public void onClick(View view) {
        clickMap.get(view.getId()).run();
    }

    public void onClickStart() {
        isRunning = true;
    }

    public void onClickStop(){
        isRunning = false;
    }

    public void onClickReset(){
        isRunning = false;
        seconds = 0;
    }

    private void runTimer(View view) {
        TextView timeView = view.findViewById(R.id.stopwatch_view);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
