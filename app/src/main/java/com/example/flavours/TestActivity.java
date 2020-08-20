package com.example.flavours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

public class TestActivity extends AppCompatActivity {
    FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter,adapter1;
    RecyclerView recyclerView,recyclerViewCategory;
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerViewCategory = findViewById(R.id.recyclerViewCategory);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Flavours");
        //display back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Query
        Query query = firebaseFirestore.collection("Cuisines");
        Query query1 = firebaseFirestore.collection("Category");

        //RecyclerOptions
        FirestoreRecyclerOptions<CuisinesModel> options = new FirestoreRecyclerOptions.Builder<CuisinesModel>()
                .setQuery(query,CuisinesModel.class)
                .build();
        FirestoreRecyclerOptions<CuisinesModel> options1 = new FirestoreRecyclerOptions.Builder<CuisinesModel>()
                .setQuery(query1,CuisinesModel.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<CuisinesModel, CuisinesViewHolder>(options) {

            @NonNull
            @Override
            public CuisinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuisine,parent,false);
                return new CuisinesViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull CuisinesViewHolder holder, int position, @NonNull CuisinesModel model) {
                holder.txtName.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.imageView);
                // Glide.with(getApplicationContext()).load(CuisinesModel.getImage().toString()).into(holder.imageView);
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        adapter1 = new FirestoreRecyclerAdapter<CuisinesModel, CuisinesViewHolder>(options1) {

            @NonNull
            @Override
            public CuisinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,parent,false);
                return new CuisinesViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull CuisinesViewHolder holder, int position, @NonNull CuisinesModel model) {
                holder.txtName.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.imageView);
                // Glide.with(getApplicationContext()).load(CuisinesModel.getImage().toString()).into(holder.imageView);
            }
        };
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategory.setAdapter(adapter1);
        }
    private class CuisinesViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private ImageView imageView;

        public CuisinesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        adapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
        adapter1.stopListening();
    }
}