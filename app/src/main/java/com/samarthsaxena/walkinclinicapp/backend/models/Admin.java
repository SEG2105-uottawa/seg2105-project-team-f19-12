package com.samarthsaxena.walkinclinicapp.backend.models;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class Admin extends User {


    public Admin(String email, String username, String hashedPassword) {
        super(email, username, hashedPassword);
        this.type = "admin";
    }

    public boolean deleteaccount(String type) {

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Users").child(type);
        dR.removeValue();
        return true;
    }


    public static ArrayList<User> alldata() {

        final ArrayList<User> user = new ArrayList<User>();

        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Users");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                user.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User artist = snapshot.getValue(User.class);
                        user.add(artist);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dbref.addListenerForSingleValueEvent(valueEventListener);

        return user;
    }

}






