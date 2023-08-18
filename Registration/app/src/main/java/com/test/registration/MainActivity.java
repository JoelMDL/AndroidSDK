package com.test.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        //get info
        EditText nameInput = findViewById(R.id.nameInput);
        String name = nameInput.getText().toString();

        EditText mailInput = findViewById(R.id.mailInput);
         String mail = mailInput.getText().toString();

        EditText passInput = findViewById(R.id.passInput);
       String pass = passInput.getText().toString();
        //confirmation preview
        TextView nameTxt = findViewById(R.id.nameTxt);
        nameTxt.setText(name);

        TextView mailTxt = findViewById(R.id.mailTxt);
        mailTxt.setText(mail);

        TextView passTxt = findViewById(R.id.passTxt);
        passTxt.setText(pass);


    }
}