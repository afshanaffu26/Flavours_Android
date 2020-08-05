package com.example.flavours;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener{
Button btnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"Payment is being Processed, Please don't refresh", Toast.LENGTH_SHORT).show();
    }
}