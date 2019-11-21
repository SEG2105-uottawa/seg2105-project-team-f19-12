package com.samarthsaxena.walkinclinicapp.backend.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;

import java.util.ArrayList;

public class Service {

    // Name of fields stored in database
    public static final String SERVICE_STRING                      = "Services";
    public static final String SERVICE_OFFERED_STRING              = "serviceOffered";
    public static final String SERVICE_ROLE_STRING                 = "role";

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

    public String getServiceOffered() {
        return serviceOffered;
    }
    public String getRole() {
        return role;
    }

    public void setServiceOffered(String serviceOffered) { this.serviceOffered = serviceOffered; }
    public void setRole(String role) { this.role = role; }

    // Store service object in database
    public void dbStore(final MyCallback cb) throws RuntimeException {

        DatabaseReference serviceRef = db.getReference().child(SERVICE_STRING).push();
        serviceRef.child(SERVICE_OFFERED_STRING)    .setValue(serviceOffered);
        serviceRef.child(SERVICE_ROLE_STRING)       .setValue(role);
        if (cb != null) {
            cb.onCallback(Service.this);
        }
    }

    // Get all services from database
    public static ArrayList<Service> dbGetAll(final String param, final String value, final MyCallback cb) {

        final ArrayList<Service> services = new ArrayList<Service>();


        // Check param type is an existing service field
        if  (
                !param.equals(SERVICE_STRING) &&
                !param.equals(SERVICE_OFFERED_STRING) &&
                !param.equals(SERVICE_ROLE_STRING) &&
                !param.equals("all")
        ) {
            cb.exceptionHandler("Invalid service parameter");
        }

        DatabaseReference ref = db.getReference().child(SERVICE_STRING);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot servSnapshot : dataSnapshot.getChildren()) {
                    if ((servSnapshot.child(param).getValue() != null &&
                            servSnapshot.child(param).getValue().toString().equals(value)) ||
                         param.equals("all")) {

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
                if (cb != null) {
                    cb.onCallback(services);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                cb.exceptionHandler(databaseError.getMessage());
            }
        });

        return services;
    }


    public static void dbEdit(String prevService, final String newService, final String newRole){
        Query dbQuery = db.getReference().child(SERVICE_STRING).orderByChild(SERVICE_OFFERED_STRING).equalTo(prevService);

        dbQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot servSnapshot: dataSnapshot.getChildren()) {
                    servSnapshot.getRef().child(SERVICE_OFFERED_STRING).setValue(newService);
                    servSnapshot.getRef().child(SERVICE_ROLE_STRING).setValue(newRole);
                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public static void dbDelete(String serviceOffered) {
        Query dbQuery = db.getReference().child(SERVICE_STRING).orderByChild(SERVICE_OFFERED_STRING).equalTo(serviceOffered);

        dbQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot servSnapshot: dataSnapshot.getChildren()) {
                    servSnapshot.getRef().removeValue();
                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
