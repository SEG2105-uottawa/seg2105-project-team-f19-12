package com.samarthsaxena.walkinclinicapp.backend.models;

public class Employee extends User {

    public Employee(int id, String firstName, String username, String hashedPassword) {
        super(id, firstName, username, hashedPassword);
        this.type = "employee";
    }
}