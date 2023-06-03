package com.example.androidlabs;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindBeerListener implements View.OnClickListener{
    private final Spinner beerSpinner;
    private final TextView beerTypeTextView;

    public FindBeerListener(final Spinner beerSpinner, final TextView beerTypeTextView) {
        this.beerSpinner = beerSpinner;
        this.beerTypeTextView = beerTypeTextView;
    }

    @Override
    public void onClick(View view) {
        beerTypeTextView.setText(getBeers(beerSpinner.getSelectedItem().toString()));
    }

    private String getBeers(final String color) {
        for(Beer beer : Beer.values()){
            if(beer.toString().toLowerCase().equals(color)){
                return beer.getBeers().stream().collect(Collectors.joining(System.lineSeparator()));
            }
        }
        return "";
    }

    private enum Beer {
        LIGHT(List.of("Bud Light", "Budweiser Original", "Staropramen")),
        DARK(List.of("Guinness Stout", "Ayinger Altbairisch Dunkel")),
        AMBER(List.of("Beck's Octoberfest", "Brooklyn Octoberfest")),
        BROWN(List.of("Grimberger"));
        private final List<String> beers;

        Beer(List<String> beers) {
            this.beers = beers;
        }

        public List<String> getBeers() {
            return beers;
        }
    }
}
