package com.samarthsaxena.walkinclinicapp.backend.models;

public class Admin extends User {

    public Admin(int id, String firstName, String username, String hashedPassword) {
        super(id, firstName, username, hashedPassword);
        this.type = "admin";
    }
}