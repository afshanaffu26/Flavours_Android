package com.example.flavours;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    TextView txt_recover_password,textSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flavours");

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        txt_recover_password= findViewById(R.id.txt_recover_password);
        txt_recover_password.setOnClickListener(this);
        textSignUp=findViewById(R.id.textView2);
        textSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                break;
            case R.id.txt_recover_password:
                startActivity(new Intent(getApplicationContext(), ForgotActivity.class));
                break;
            case R.id.textView2:
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));

            }
        }
            }

