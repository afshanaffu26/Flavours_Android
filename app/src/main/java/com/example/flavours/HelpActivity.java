package com.example.flavours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {
    TextView help2;
    TextView help3;
    TextView help4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        help2=findViewById(R.id.help2);
        help2.setOnClickListener(this);
        help3=findViewById(R.id.help3);
        help3.setOnClickListener(this);
        help4=findViewById(R.id.help4);
        help4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.help2:
                startActivity(new Intent(getApplicationContext(), FaqActivity.class));
            case R.id.help3:
                startActivity(new Intent(getApplicationContext(), ContactActivity.class));
        }
    }
}