package com.arsenii.android.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReceiveMessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_message_activity);
        Intent intent = getIntent();
        TextView textView = findViewById(R.id.sentTextView);
        textView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
    }
}
