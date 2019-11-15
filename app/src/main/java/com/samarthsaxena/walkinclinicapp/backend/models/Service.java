package com.samarthsaxena.walkinclinicapp.backend.models;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;

import java.util.ArrayList;

public class Service {

    // Name of fields stored in database
    private static final String SERVICE_STRING                      = "Services";
    private static final String SERVICE_OFFERED_STRING              = "service offered";
    private static final String SERVICE_ROLE_STRING                 = "role";

    private  String serviceOffered;
    private  String role;

    private static final FirebaseDatabase db = FirebaseDatabase.getInstance();

    public Service() {
       //Empty constructor
    }

    public Service(String serviceoffered, String role){
        this.serviceOffered = serviceoffered;
        this.role = role;
    }

    public String getServiceoffered() {
        return serviceOffered;
    }
    public String getRole() {
        return role;
    }

    // Store service object in database
    public void dbStore(final MyCallback cb) throws RuntimeException {

        DatabaseReference serviceRef = db.getReference().child(SERVICE_STRING).push();
        serviceRef.child(SERVICE_OFFERED_STRING)    .setValue(serviceOffered);
        serviceRef.child(SERVICE_ROLE_STRING)       .setValue(role);
        cb.onCallback(Service.this);
    }

    // Get all services from database
    public static ArrayList<Service> dbGetAll(final String param, final String value, final MyCallback cb) {

        final ArrayList<Service> services = new ArrayList<Service>();


        // Check param type is an existing service field
        if  (
                !param.equals(SERVICE_STRING) &&
                        !param.equals(SERVICE_OFFERED_STRING) &&
                        !param.equals(SERVICE_ROLE_STRING)
        ) {
            cb.exceptionHandler("Invalid service parameter");
        }

        DatabaseReference ref = db.getReference().child(SERVICE_STRING);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot servSnapshot : dataSnapshot.getChildren()) {
                    if (servSnapshot.child(param).getValue() != null &&
                            servSnapshot.child(param).getValue().toString().equals(value)) {

                        String serviceOffered = "";
                        String role = "";

                        Service service;
                        try {
                            serviceOffered = servSnapshot.child(SERVICE_OFFERED_STRING).getValue().toString();
                            role = servSnapshot.child(SERVICE_ROLE_STRING).getValue().toString();
                        } catch (NullPointerException e) {
                            break;
                        }
                        service = new Service(serviceOffered, role);
                        services.add(service);
                    }
                }
                cb.onCallback(services);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                cb.exceptionHandler(databaseError.getMessage());
            }
        });

        return services;
    }



    protected void updateService(String id, String serviceoffered, String role){
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("service").child(id);
        Service service = new Service(serviceoffered, role);
        dR.setValue(service);
    }

    protected void removeService(String id) {
        DatabaseReference dRService = FirebaseDatabase.getInstance().getReference("service").child(id);
        dRService.removeValue();
    }

}
