package com.arsenii.android.lab7;

import android.app.TabActivity;
import android.content.Intent;
import android.widget.TabHost;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends TabActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec("firstTag");
        tabSpec.setIndicator("First tab");
        tabSpec.setContent(new Intent(this, FirstTab.class));
        tabHost.addTab(tabSpec);
        tabSpec = tabHost.newTabSpec("secondTag");
        tabSpec.setIndicator("Second tab");
        tabSpec.setContent(new Intent(this, SecondTab.class));
        tabHost.addTab(tabSpec);
    }
}