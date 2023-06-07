package com.arsenii.android.lab5;

import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    private EditText idEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText addressEditText;
    private ImageView imageView;
    private Button updateButton;
    private Button deleteButton;
    private Button addButton;
    private Button readButton;
    private Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idEditText = findViewById(R.id.editTextId);
        firstNameEditText = findViewById(R.id.editTextFirstName);
        lastNameEditText = findViewById(R.id.editTextSecondName);
        emailEditText = findViewById(R.id.editTextEmail);
        addressEditText = findViewById(R.id.editTextAddress);
        imageView = findViewById(R.id.imageView);

        dbManager = new DBManager(this);

        updateButton = findViewById(R.id.buttonUpdate);
        updateButton.setOnClickListener(
                click -> dbManager.update(
                        idEditText.getText().toString(),
                        firstNameEditText.getText().toString(),
                        lastNameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        addressEditText.getText().toString(),
                        "image"
                )
        );

        deleteButton = findViewById(R.id.buttonDelete);
        deleteButton.setOnClickListener(
                click -> dbManager.deleteUser(idEditText.getText().toString())
        );

        addButton = findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(
                click -> dbManager.addUser(
                        idEditText.getText().toString(),
                        firstNameEditText.getText().toString(),
                        lastNameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        addressEditText.getText().toString(),
                        "image"
                )
        );

        readButton = findViewById(R.id.buttonRead);
        readButton.setOnClickListener(click -> {
            Optional<User> user = dbManager.select(idEditText.getText().toString());
            if(user.isPresent()){
                firstNameEditText.setText(user.get().getFirstName());
                lastNameEditText.setText(user.get().getLastName());
                emailEditText.setText(user.get().getEmail());
                addressEditText.setText(user.get().getAddress());
            } else {
                Toast.makeText(this, "Cannot ", Toast.LENGTH_LONG).show();
            }
        });

        clearButton = findViewById(R.id.buttonClear);
        clearButton.setOnClickListener(click -> {
            idEditText.setText("");
            firstNameEditText.setText("");
            lastNameEditText.setText("");
            emailEditText.setText("");
            addressEditText.setText("");
        });
    }
}