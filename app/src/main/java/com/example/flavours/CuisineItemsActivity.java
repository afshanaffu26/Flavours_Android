package com.example.flavours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class CuisineItemsActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    RecyclerView recyclerView;
    String cuisine ="";
    TextView txtCuisine;
    String documentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine_items);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flavours");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        txtCuisine = findViewById(R.id.txtCuisine);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Query query;
        cuisine = getIntent().getStringExtra("name");
        if (cuisine.equals("Indian"))
            query = firebaseFirestore.collection("IndianMenu");
        else if (cuisine.equals("Chinese"))
            query = firebaseFirestore.collection("ChineseMenu");
        else
            query = firebaseFirestore.collection("VeganMenu");

        txtCuisine.setText(cuisine+" Cuisine");

        //RecyclerOptions
        FirestoreRecyclerOptions<CuisineItemsModel> options = new FirestoreRecyclerOptions.Builder<CuisineItemsModel>()
                .setQuery(query,CuisineItemsModel.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<CuisineItemsModel, CuisineItemsActivity.CuisinesItemsViewHolder>(options) {

            @NonNull
            @Override
            public CuisineItemsActivity.CuisinesItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,parent,false);
                return new CuisineItemsActivity.CuisinesItemsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull CuisineItemsActivity.CuisinesItemsViewHolder holder, final int position, @NonNull final CuisineItemsModel model) {
                holder.txtName.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.imageView);
                // Glide.with(getApplicationContext()).load(CuisineItemsModel.getImage().toString()).into(holder.imageView);
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), ItemDescriptionActivity.class);
                        intent.putExtra("cuisine", cuisine);
                        intent.putExtra("name", model.getName());
                        intent.putExtra("image", model.getImage());
                        intent.putExtra("price", model.getPrice());
                        intent.putExtra("desc", model.getDesc());
                        intent.putExtra("ingredients", model.getIngredients());
                        documentId = getSnapshots().getSnapshot(position).getId();
                        intent.putExtra("documentId", documentId);
                        startActivity(intent);
                    } });
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
    private class CuisinesItemsViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private ImageView imageView;
        private LinearLayout linearLayout;
        public CuisinesItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            imageView = itemView.findViewById(R.id.imageView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.item1:
                showItemDescription();
                break;
        }

    }
    private void showItemDescription() {
        Intent i = new Intent(CuisineItemsActivity.this,ItemDescriptionActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}