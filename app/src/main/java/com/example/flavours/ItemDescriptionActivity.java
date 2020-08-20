package com.example.flavours;

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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemDescriptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final int MAX_QUANTITY = 20;
    Button btnAddToCart,btnBuyNow;
    ImageView imageView;
    TextView txtName,txtPrice,txtDesc,txtIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBuyNow = findViewById(R.id.btnBuyNow);
        btnBuyNow.setOnClickListener(this);
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

        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        String price = getIntent().getStringExtra("price");
        String desc = getIntent().getStringExtra("desc");

        String ingredients = getIntent().getStringExtra("ingredients");
        ingredients = ingredients.replaceAll( "\\\\n", "\n" );

        Picasso.get().load(image).into(imageView);
        txtName.setText(name);
        txtPrice.setText("Price : " + price+"$");
        txtDesc.setText(desc);
        txtIngredients.setText(ingredients);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

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
                Toast.makeText(this,"Items added to cart succesfully.",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnBuyNow:
                startActivity(new Intent(getApplicationContext(), AddressActivity.class));
                break;
        }
    }
}