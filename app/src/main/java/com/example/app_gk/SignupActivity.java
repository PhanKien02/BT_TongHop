package com.example.app_gk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        //  singin User
        TextView textViewRegister = findViewById(R.id.txt_singin);
        Button btnSignup = (Button) findViewById(R.id.button);
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(registerActivity);
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerActivity = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(registerActivity);
            }
        });
    }
}