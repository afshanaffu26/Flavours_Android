package com.example.flavours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnPay;
    FirebaseFirestore firebaseFirestore;
    String uid;
    String subTotal,deliveryCharge,tax,total;
    String address;
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        btnPay = findViewById(R.id.btnPay);
        btnPay.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flavours");
        //display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseFirestore = FirebaseFirestore.getInstance();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    }@Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(), "Payment is being Processed, Please don't refresh", Toast.LENGTH_SHORT).show();
        cartCheckout();
    }
    private void cartCheckout() {
        final String docId = "" + UUID.randomUUID().toString();
        firebaseFirestore.collection("Address").document(uid).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        address = documentSnapshot.get("address").toString();
                    }
                });
        firebaseFirestore.collection("Cart").document("cart" + uid).collection("cart").get().addOnCompleteListener(
                new OnCompleteListener < QuerySnapshot > () {@Override
                public void onComplete(@NonNull Task < QuerySnapshot > task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot documentSnapshot: task.getResult()) {
                            CuisineItemsModel cuisineItemsModel = documentSnapshot.toObject(CuisineItemsModel.class);
                            firebaseFirestore.collection("Orders").document("orders" + uid).collection("orders").document(docId).collection("Order").add(cuisineItemsModel).addOnSuccessListener(new OnSuccessListener < DocumentReference > () {@Override
                            public void onSuccess(DocumentReference documentReference) {
                                String date = new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date());
                                subTotal = getIntent().getStringExtra("subTotal");
                                tax = getIntent().getStringExtra("tax");
                                deliveryCharge = getIntent().getStringExtra("deliveryCharge");
                                total = getIntent().getStringExtra("total");

                                OrdersModel ordersModel = new OrdersModel(date, subTotal, tax, deliveryCharge, total, address);
                                firebaseFirestore.collection("Orders").document("orders" + uid).collection("orders").document(docId).set(ordersModel)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                firebaseFirestore.collection("Cart").document("cart" + uid).collection("cart").get().addOnCompleteListener(new OnCompleteListener < QuerySnapshot > () {
                                                    @Override
                                                    public void onComplete(@NonNull Task < QuerySnapshot > task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(getApplicationContext(), "Order placed successfully.", Toast.LENGTH_SHORT).show();
                                                            for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()) {
                                                                firebaseFirestore.collection("Cart").document("cart" + uid).collection("cart").document(queryDocumentSnapshot.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                    @Override
                                                                    public void onSuccess(Void aVoid) {
                                                                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                                                    }
                                                                });
                                                            }
                                                        }
                                                    }
                                                });
                                            }
                                        });
                            }
                            });

                        }
                    }
                }
                }).addOnFailureListener(new OnFailureListener() {@Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        });
    }
}