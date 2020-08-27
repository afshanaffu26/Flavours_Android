package com.example.flavours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ItemDescriptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final int MAX_QUANTITY = 6;
    Button btnAddToCart;
    ImageView imageView;
    TextView txtName,txtPrice,txtDesc,txtIngredients;
    FirebaseFirestore firebaseFirestore;
    String name,image,price,quantity,id;
    Spinner spinner;
    boolean isUpdated = false;

    private static ArrayList<Type> mArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        firebaseFirestore = FirebaseFirestore.getInstance();

        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnAddToCart.setOnClickListener(this);

        imageView = findViewById(R.id.imageView);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.price);
        txtDesc = findViewById(R.id.txtDesc);
        txtIngredients = findViewById(R.id.txtIngredients);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flavours");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = getIntent().getStringExtra("name");
        image = getIntent().getStringExtra("image");
        price = getIntent().getStringExtra("price");
        String desc = getIntent().getStringExtra("desc");
        id = getIntent().getStringExtra("id");

        String ingredients = getIntent().getStringExtra("ingredients");
        ingredients = ingredients.replaceAll( "\\\\n", "\n" );

        Picasso.get().load(image).into(imageView);
        txtName.setText(name);
        txtPrice.setText("Price : " + price+"$");
        txtDesc.setText(desc);
        txtIngredients.setText(ingredients);

        spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<Integer> categories = new ArrayList<>();
        for (int i=1;i<=MAX_QUANTITY;i++){
            categories.add(i);
        }
        // Creating adapter for spinner
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    public void addData(String name, String image, final String quantity, String price, final String id){
        isUpdated = false;
        firebaseFirestore.collection("Cart")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        CartModel cartModel = documentSnapshot.toObject(CartModel.class);
                        if (cartModel.getId().equals(id)) {
                            firebaseFirestore.collection("Cart").document(documentSnapshot.getId()).update("quantity", quantity);
                            isUpdated = true;
                        }
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        if(!isUpdated) {
            addItemToCart();
        }
    }

    private void addItemToCart() {
        CartModel cartModel = new CartModel(name, image, price, quantity, id);
        firebaseFirestore.collection("Cart")
                .add(cartModel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Items added to cart succesfully.",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAddToCart:
                quantity = spinner.getSelectedItem().toString();
                addData(name,image,quantity,price,id);
                break;
        }
    }
}