package com.example.hp.afinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyBookings extends AppCompatActivity implements View.OnClickListener{

    ListView listViewBookings;

    DatabaseReference databaseReference;

    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

    List<ServiceDetail> serviceDetailList;

    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        String id = getIntent().getStringExtra("id");

        listViewBookings = (ListView) findViewById(R.id.listViewBookings);

        serviceDetailList = new ArrayList<>();

        final TextView sName = (TextView)findViewById(R.id.sName);

        home = (Button) findViewById(R.id.home);

        home.setOnClickListener(this);


        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Bookings");
       // String id = databaseReference.push().getKey();

        databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                serviceDetailList.clear();


                for (DataSnapshot bookingSnapshot: dataSnapshot.getChildren()){
                    ServiceDetail serviceDetail = bookingSnapshot.getValue(ServiceDetail.class);

                    //sName.setText(serviceDetail.getServiceName());
                    serviceDetailList.add(serviceDetail);
                }

                BookingList adapter = new BookingList(MyBookings.this,serviceDetailList);
                listViewBookings.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == home)
        {
            Intent intent = new Intent(this,NavigationDrawerActivity.class);
            finish();
            startActivity(intent);
        }
    }
}

