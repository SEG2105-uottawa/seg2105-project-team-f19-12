package com.samarthsaxena.walkinclinicapp.backend.models;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;





public class Admin extends User {


    public Admin(String email, String username, String hashedPassword) {
        super(email, username, hashedPassword);
        this.type = "admin";
    }

    public boolean deleteaccount(String type){

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Users").child(type);
        dR.removeValue();
        return true;
    }

        }



