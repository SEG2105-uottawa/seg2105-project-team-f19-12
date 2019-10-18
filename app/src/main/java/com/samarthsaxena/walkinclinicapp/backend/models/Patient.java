package com.samarthsaxena.walkinclinicapp.backend.models;


public class Patient extends User {

    public Patient(String email, String username, String hashedPassword) {
        super(email, username, hashedPassword);
        this.type = "patient";
        dbStore();
    }
}
