package com.example.flavours;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderHistoryFragment extends Fragment {
    FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    RecyclerView recyclerView;
    String orderHistoryDocumentId;
    LinearLayout linearLayout;
    TextView txtEmptyOrders;
    String uid;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderHistoryFragment newInstance(String param1, String param2) {
        OrderHistoryFragment fragment = new OrderHistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called to do initial creation of a fragment
     * Note that this can be called while the fragment's activity is still in the process of being created. As such, you can not rely on things like the activity's content view hierarchy being initialized at this point. If you want to do work once the activity itself is created, add a {@link androidx.lifecycle.LifecycleObserver} on the activity's Lifecycle, removing it when it receives the Lifecycle.State.CREATED callback.
     * @param savedInstanceState If the fragment is being re-created from a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * Called to have the fragment instantiate its user interface view. This is optional, and non-graphical fragments can return null. This will be called between onCreate(Bundle) and onViewCreated(View, Bundle).
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to. The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order_history, container, false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = v.findViewById(R.id.recyclerView);
        linearLayout = v.findViewById(R.id.linearLayout);
        txtEmptyOrders = v.findViewById(R.id.txtEmptyOrders);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        Query query = firebaseFirestore.collection("Orders").document("orders"+uid).collection("orders");
        FirestoreRecyclerOptions<OrdersModel> options = new FirestoreRecyclerOptions.Builder<OrdersModel>()
                .setQuery(query,OrdersModel.class)
                .build();
        adapter = new FirestoreRecyclerAdapter<OrdersModel, OrderHistoryFragment.OrderHistoryViewHolder>(options) {

            @NonNull
            @Override
            public OrderHistoryFragment.OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history,parent,false);
                return new OrderHistoryFragment.OrderHistoryViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull OrderHistoryFragment.OrderHistoryViewHolder holder, final int position, @NonNull final OrdersModel model) {
                holder.txtOrderDate.setText("Ordered On: "+model.getDate());
                holder.txtSubTotal.setText("Subtotal: "+model.getSubtotal());
                holder.txtTax.setText("Tax: "+model.getTax());
                holder.txtDeliveryCharge.setText("Delivery Charge: "+model.getDeliveryCharge());
                holder.txtTotal.setText("Total: "+model.getTotal());
                holder.txtAddress.setText("Address: "+model.getAddress());
                if (adapter.getItemCount() != 0)
                {
                    txtEmptyOrders.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    txtEmptyOrders.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                }
                holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), OrderHistoryListActivity.class);
                        //DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
                        orderHistoryDocumentId = getSnapshots().getSnapshot(position).getId();
                        intent.putExtra("orderHistoryDocumentId", orderHistoryDocumentId);
                        startActivity(intent);
                    } });
            }
        };
        recyclerView.setHasFixedSize(false);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
        return v;
    }

    /**
     * This class binds the recycler view with item
     * Subclass of {@link RecyclerView.ViewHolder}
     */
    private class OrderHistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView txtOrderDate,txtSubTotal,txtTax,txtDeliveryCharge,txtTotal,txtAddress;
        private LinearLayout linearLayout;
        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderDate = itemView.findViewById(R.id.txtOrderDate);
            txtSubTotal = itemView.findViewById(R.id.txtSubTotal);
            txtTax = itemView.findViewById(R.id.txtTax);
            txtDeliveryCharge = itemView.findViewById(R.id.txtDeliveryCharge);
            txtTotal = itemView.findViewById(R.id.txtTotal);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}