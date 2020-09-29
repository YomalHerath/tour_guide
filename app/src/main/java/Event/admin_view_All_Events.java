package Event;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import Event.MyViewHolder;
import Event.addNewEvent;
import com.example.tour_guide.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class admin_view_All_Events extends AppCompatActivity {
    EditText inputSearch;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    FirebaseRecyclerOptions<addNewEvent> options;
    FirebaseRecyclerAdapter<addNewEvent, MyViewHolder> adapter;

    //FireBase Reference
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view__all__events);

        floatingActionButton = findViewById(R.id.floatingBtn);
        inputSearch = findViewById(R.id.search);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Events");

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), addEvent.class));
            }
        });
        LoadData("");

        //searching
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            //public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString() != null) {
                    LoadData(s.toString());
                } else {
                    LoadData("");
                }
            }
        });
    }

    //View All the Events
    private void LoadData(String d) {
        options = new FirebaseRecyclerOptions.Builder<addNewEvent>().setQuery(databaseReference, addNewEvent.class).build();
        adapter = new FirebaseRecyclerAdapter<addNewEvent, MyViewHolder>(options) {

            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull addNewEvent model) {
                holder.txtVEventName.setText(model.getEventName());
                holder.txtVDesc.setText(model.getDescription());
                Picasso.get().load(model.getImageUrl()).into(holder.imageView);


                //Direct to the Events details screen
                holder.txtVEventName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(admin_view_All_Events.this, AdminViewEventSingleDetails.class);
                        intent.putExtra("PlaceKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

                //Direct to the Events details screen
                holder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(admin_view_All_Events.this, AdminViewEventSingleDetails.class);
                        intent.putExtra("PlaceKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

                holder.txtVDesc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(admin_view_All_Events.this, AdminViewEventSingleDetails.class);
                        intent.putExtra("PlaceKey", getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_items, parent, false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}