package com.example.flavours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
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
    String name,image,price,quantity,id,desc,ingredients,documentId;
    Spinner spinner;

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

        name = getIntent().getStringExtra("name");
        image = getIntent().getStringExtra("image");
        price = getIntent().getStringExtra("price");
        desc = getIntent().getStringExtra("desc");
        ingredients = getIntent().getStringExtra("ingredients");
        ingredients = ingredients.replaceAll( "\\\\n", "\n" );
        quantity = getIntent().getStringExtra("quantity");
        documentId = getIntent().getStringExtra("documentId");

        Picasso.get().load(image).into(imageView);
        txtName.setText(name);
        txtPrice.setText("Price : " + price+"$");
        txtDesc.setText(desc);
        txtIngredients.setText(ingredients);
        spinner.setSelection(getIndex(spinner, quantity));

    }
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    private void addItemToCart(String name, String image, String quantity, String price,String desc,String ingredients) {
        CuisineItemsModel cuisineItemsModel = new CuisineItemsModel(name, image, price,desc,ingredients,quantity);
        firebaseFirestore.collection("Cart").document(documentId)
                .set(cuisineItemsModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
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
                addItemToCart(name,image,quantity,price,desc,ingredients);
                break;
        }
    }
}