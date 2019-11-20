package com.samarthsaxena.walkinclinicapp.backend.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;

public class UserService {

    private static final String USERSERVICE_STRING = "UserServiceAssociation";
    private static final String USERSERVICE_USER_STRING = "User";
    private static final String USERSERVICE_SERVICE_STRING = "Service";

    private String user;
    private String service;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();

    public UserService(String user, String service) {
        this.user = user;
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
}
