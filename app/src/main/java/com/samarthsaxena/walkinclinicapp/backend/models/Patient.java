package com.samarthsaxena.walkinclinicapp.backend.models;

public class Patient extends User {

    public Patient(int id, String firstName, String username, String hashedPassword) {
        super(id, firstName, username, hashedPassword);
        this.type = "patient";
    }
}
