package com.example.flavours;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    TextView conditions,textLogin;
    Button btnSignUp;
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        conditions = findViewById(R.id.conditions);
        conditions.setOnClickListener(this);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
        textLogin=findViewById(R.id.textLogin);
        textLogin.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flavours");
        //display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.conditions:
                startActivity(new Intent(getApplicationContext(), TermsActivity.class));
                break;
            case R.id.btnSignUp:
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                break;
            case R.id.textLogin:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));

        }
    }
}