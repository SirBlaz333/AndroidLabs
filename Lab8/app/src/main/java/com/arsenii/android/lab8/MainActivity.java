package com.arsenii.android.lab8;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import androidx.annotation.Nullable;

public class MainActivity extends Activity implements WorkoutListFragment.WorkoutListListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        WorkoutDetailFragment details = new WorkoutDetailFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        details.setWorkout(id);
        fragmentTransaction.replace(R.id.fragment_container, details);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

        StopwatchFragment stopwatchFragment = new StopwatchFragment();
        FragmentTransaction stopwatchFragmentTransaction = getFragmentManager().beginTransaction();
        stopwatchFragmentTransaction.replace(R.id.fragment_container2, stopwatchFragment);
        stopwatchFragmentTransaction.addToBackStack(null);
        stopwatchFragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        stopwatchFragmentTransaction.commit();
    }
}
