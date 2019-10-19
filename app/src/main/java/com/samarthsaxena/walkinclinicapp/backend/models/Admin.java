package com.samarthsaxena.walkinclinicapp.backend.models;

public class Admin extends User {

    public Admin(String email, String username, String hashedPassword) {
        super(email, username, hashedPassword);
        this.type = "admin";
    }
}