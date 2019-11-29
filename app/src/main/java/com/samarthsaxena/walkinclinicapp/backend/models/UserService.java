package com.samarthsaxena.walkinclinicapp.backend.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;

import java.util.ArrayList;

public class UserService {

    public static final String USERSERVICE_STRING = "UserServiceAssociation";
    public static final String USERSERVICE_USER_STRING = "User";
    public static final String USERSERVICE_SERVICE_STRING = "Service";

    private String user;
    private String service;
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();

    public UserService(String user, String service) {
        this.user = user;
        this.service = service;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void dbStore(final MyCallback cb) throws RuntimeException {

        DatabaseReference serviceRef = db.getReference().child(USERSERVICE_STRING).push();
        serviceRef.child(USERSERVICE_USER_STRING)           .setValue(user);
        serviceRef.child(USERSERVICE_SERVICE_STRING)        .setValue(service);
        if (cb != null) {
            cb.onCallback(UserService.this);
        }
    }

    public static ArrayList<UserService> dbGetAll(final String param, final String value, final MyCallback cb) {

        final ArrayList<UserService> userServices = new ArrayList<>();


        // Check param type is an existing user field
        if  (
                !param.equals(USERSERVICE_USER_STRING) &&
                        !param.equals(USERSERVICE_SERVICE_STRING) &&
                        !param.equals("all")
        ) {
            cb.exceptionHandler("Invalid userService parameter");
        }

        DatabaseReference ref = db.getReference().child(USERSERVICE_STRING);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    if ((userSnapshot.child(param).getValue() != null &&
                            userSnapshot.child(param).getValue().toString().equals(value)) ||
                            param.equals("all")) {

                        String user = "";
                        String service = "";

                        UserService userService;
                        try {
                            user = userSnapshot.child(USERSERVICE_USER_STRING).getValue().toString();
                            service = userSnapshot.child(USERSERVICE_SERVICE_STRING).getValue().toString();
                        } catch (NullPointerException e) {
                            break;
                        }

                        userService = new UserService(user, service);
                        userServices.add(userService);
                    }
                }
                if (cb != null) {
                    cb.onCallback(userServices);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                cb.exceptionHandler(databaseError.getMessage());
            }
        });

        return userServices;
    }

    public static void dbDelete(String user, final String service) {

        Query dbQuery = db.getReference().child(USERSERVICE_STRING).orderByChild(USERSERVICE_USER_STRING).equalTo(user);

        dbQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot servSnapshot: dataSnapshot.getChildren()) {
                    if (servSnapshot.child(USERSERVICE_SERVICE_STRING).getValue().equals(service)) {
                        servSnapshot.getRef().removeValue();
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void dbSetRating(final int rating, final String service) {

        Query dbQuery = db.getReference().child(USERSERVICE_STRING).orderByChild(USERSERVICE_USER_STRING).equalTo(this.user);

        dbQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot servSnapshot: dataSnapshot.getChildren()) {
                    if (servSnapshot.child(USERSERVICE_USER_STRING).getValue().equals(user) &&
                            servSnapshot.child(USERSERVICE_SERVICE_STRING).getValue().equals(service)) {
                        servSnapshot.getRef().child("rating").setValue(rating);
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
