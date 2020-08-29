package com.example.flavours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnNext;
    EditText editName,editApt,editAddress,editCity,editPostalID;
    String name,apt,add,city,postalID;
    FirebaseFirestore firebaseFirestore;
    String uid;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flavours");
        //display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editName = findViewById(R.id.editName);
        editApt = findViewById(R.id.editApt);
        editAddress = findViewById(R.id.editAddress);
        editCity = findViewById(R.id.editCity);
        editPostalID = findViewById(R.id.editPostalID);

        firebaseFirestore = FirebaseFirestore.getInstance();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


    }

    @Override
    public void onClick(View view) {
        name = editName.getText().toString();
        apt = editApt.getText().toString();
        add = editAddress.getText().toString();
        city = editCity.getText().toString();
        postalID = editPostalID.getText().toString();

        switch (view.getId()) {
            case R.id.btnNext:
                addAddress();
                break;
        }
    }

    private void addAddress() {
        Map<String, Object> address = new HashMap<>();
        address.put("address", ""+name+" "+apt+" "+add+" "+city+" "+postalID);
        firebaseFirestore.collection("Address").document(uid).set(address)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(getApplicationContext(), PaymentActivity.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddressActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}