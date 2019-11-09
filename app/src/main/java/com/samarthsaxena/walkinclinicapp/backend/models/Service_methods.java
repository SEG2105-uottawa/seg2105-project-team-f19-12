package com.samarthsaxena.walkinclinicapp.backend.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Service_methods {

    DatabaseReference dbservice;
    final List<Service> serviceList = null;

   public Service_methods(){

   }

    protected void addService() {


        dbservice.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                serviceList.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Service service = postSnapshot.getValue(Service.class);

                    serviceList.add(service);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
