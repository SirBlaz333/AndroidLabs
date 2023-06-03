package com.arsenii.android.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SendMessageActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_message_activity);
        Button button = findViewById(R.id.send_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView textView = findViewById(R.id.user_text);
        Intent intent = new Intent(getApplicationContext(), ReceiveMessageActivity.class);
        intent.setAction(Intent.ACTION_SEND);
        intent.setAction("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, textView.getText().toString());
        startActivity(intent);
    }
}
