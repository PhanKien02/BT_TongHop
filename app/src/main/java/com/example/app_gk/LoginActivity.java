package com.example.app_gk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Button btnLogin =(Button) findViewById(R.id.log_in);
        Button btnSingup = (Button) findViewById(R.id.txt_regiter);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginActivity = new Intent(LoginActivity.this,MainActivity.class);
                EditText username = (EditText) findViewById(R.id.txt_username) ;
                EditText pass = (EditText) findViewById(R.id.txt_password);
                TextView err =  findViewById(R.id.textView2);
                String user="kien1103";
                String password="123456";
                if(username.getText().toString().equals(user)  && pass.getText().toString().equals(password) ){
                    startActivity(loginActivity);
                }
                if(username.getText().toString().equals("")  || pass.getText().toString().equals("") ){
                    err.setText("Vui lòng nhập username password");
                }
                else{

                    err.setText("Username hoặc password không đúng");
                }
            }
        });
        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(registerActivity);
            }
        });
    }
}