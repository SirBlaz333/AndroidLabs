package com.arsenii.android.lab4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;

public class PhoneActivity extends Activity implements View.OnClickListener {
    Button button;
    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        button = findViewById(R.id.mapButton);
        editText = findViewById(R.id.edit_query);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("tel: " + editText.getText().toString()));
        startActivity(intent);
    }
}
