package com.example.androidlabs;

import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class FindBeerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button findBeerButton = findViewById(R.id.find_beer_button);
        final Spinner beerSpinner = findViewById(R.id.beer_spinner);
        final TextView beerTypeTextView = findViewById(R.id.beer_type_text_view);
        final View.OnClickListener onClickListener = new FindBeerListener(beerSpinner, beerTypeTextView);
        findBeerButton.setOnClickListener(onClickListener);
    }
}