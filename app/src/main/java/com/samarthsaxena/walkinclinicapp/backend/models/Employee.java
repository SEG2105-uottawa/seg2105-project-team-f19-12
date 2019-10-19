package com.samarthsaxena.walkinclinicapp.backend.models;

public class Employee extends User {

    public Employee(String email, String username, String hashedPassword) {
        super(email, username, hashedPassword);
        this.type = "employee";
    }
}