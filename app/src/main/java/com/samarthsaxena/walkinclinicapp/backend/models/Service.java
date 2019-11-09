package com.samarthsaxena.walkinclinicapp.backend.models;

import com.google.firebase.database.DatabaseReference;

public class Service {

    private  String serviceoffered;
    private  String role;
    private  String id;

    public Service() {
       //Empty constructor
    }

    public Service(String id,String serviceoffered, String role){
        this.serviceoffered=serviceoffered;
        this.role=role;
        this.id=id;

    }

    public Service(String serviceoffered, String role){
        this.serviceoffered=serviceoffered;
        this.role=role;

    }

    public String getServiceoffered() {
        return serviceoffered;
    }

    public String getRole() {
        return role;
    }
    public String getId() {
        return id;
    }

}
