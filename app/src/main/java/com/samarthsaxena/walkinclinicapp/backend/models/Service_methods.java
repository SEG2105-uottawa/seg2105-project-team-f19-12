package com.samarthsaxena.walkinclinicapp.backend.models;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.samarthsaxena.walkinclinicapp.frontend.WelcomeActivity;

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

    protected void updateService(String id, String serviceoffered, String role){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("service").child(id);
        Service service = new Service(id, serviceoffered, role);
        dR.setValue(service);
    }

    protected void removeService(String id) {
        DatabaseReference dRService = FirebaseDatabase.getInstance().getReference("service").child(id);
        dRService.removeValue();
    }
}
