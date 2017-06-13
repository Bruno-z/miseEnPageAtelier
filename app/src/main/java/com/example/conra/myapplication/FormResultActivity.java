package com.example.conra.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FormResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_result);

        Intent intent = getIntent();
        String firstnameValue = intent.getStringExtra("firstname");
        String lastnameValue = intent.getStringExtra("lastname");
        String loginValue = intent.getStringExtra("login");
        String passwordValue = intent.getStringExtra("password");

        TextView firstname = (TextView) findViewById(R.id.result_firstname);
        TextView lastname = (TextView) findViewById(R.id.result_lastname);
        TextView login = (TextView) findViewById(R.id.result_login);
        TextView password = (TextView) findViewById(R.id.result_password);
        firstname.setText(firstnameValue);
        lastname.setText(lastnameValue);
        login.setText(loginValue);
        password.setText(passwordValue);
    }
}
