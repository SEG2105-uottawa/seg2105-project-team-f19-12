package com.samarthsaxena.walkinclinicapp.backend.models;

public class User {

    protected int id;
    protected String firstName;
    protected String username;
    protected String hashedPassword;
    protected String type;

    public User(int id, String firstName, String username, String hashedPassword) {
        this.id = id;
        this.firstName = firstName;
        this.username = username;
        this.hashedPassword = hashedPassword;
    }
}
