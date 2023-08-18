package com.test.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onBtnClick(View view) {
        EditText nameInput = findViewById(R.id.nameInput);
        nameInput.getText().toString();

        TextView txtHello = findViewById(R.id.helloWorldText);
        txtHello.setText("Hello " + nameInput.getText().toString());
    }
}