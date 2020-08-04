package com.example.flavours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    TextView conditions;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        conditions = findViewById(R.id.conditions);
        conditions.setOnClickListener(this);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.conditions:
                startActivity(new Intent(getApplicationContext(), TermsActivity.class));
                break;
            case R.id.btnSignUp:
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
    }
}