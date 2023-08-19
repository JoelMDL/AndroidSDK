package com.test.balanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button plusBtn;
    private Button minusBtn;
    double balance = 0;
    TextView balanceNum = findViewById(R.id.balanceNum);
    EditText balanceInput = findViewById(R.id.balanceInput);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void plusClick(View view) {

        balanceNum.setText("Hello " + balanceInput);
    }

}