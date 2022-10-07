package com.example.app_gk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ed_username = findViewById(R.id.su_username);
                EditText ed_password = findViewById(R.id.su_password);
                EditText ed_passcf = findViewById(R.id.su_confirmpassword);
                TextView tv_error = findViewById(R.id.su_er);
                Intent registerActivity = new Intent(SignupActivity.this,LoginActivity.class);
                if(ed_username.getText().toString().equals("") || ed_password.getText().toString().equals("") || ed_passcf.getText().toString().equals("") )
                {
                    tv_error.setText("Vui lòng nhập username password");
                }
                else
                if(ed_password.getText().toString().equals(ed_passcf.getText().toString()))
                {
                    startActivity(registerActivity);

                }
                else{
                    tv_error.setText("password không khớp");
                }
            }
        });
    }
}