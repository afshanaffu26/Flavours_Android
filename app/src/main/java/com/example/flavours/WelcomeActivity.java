package com.example.flavours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Button btn= findViewById(R.id.button2);
        boolean isFirstTime = MyPreferences.isFirst(WelcomeActivity.this);
        if (!isFirstTime) {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Welcome2Activity.class));

            }
        });
    }

}