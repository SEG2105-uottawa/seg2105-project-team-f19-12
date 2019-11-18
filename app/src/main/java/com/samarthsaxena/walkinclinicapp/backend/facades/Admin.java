package com.samarthsaxena.walkinclinicapp.backend.facades;

import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.models.Service;
import com.samarthsaxena.walkinclinicapp.backend.models.User;

import java.util.ArrayList;

public class Admin {

    private Admin() {

    }

    public static ArrayList<User> getAllUsers(final MyCallback cb) {
        return User.dbGetAll("all", null, new MyCallback() {
            @Override
            public void onCallback(Object value) {

            }

            @Override
            public void exceptionHandler(String message) {
                cb.exceptionHandler("Error: Could not load users from database");
            }
        });
    }

    public static void deleteUser(String username) {
        User.dbDelete(username);
    }

    public static ArrayList<Service> getAllServices(final MyCallback cb) {
        return Service.dbGetAll("all", null, new MyCallback() {
            @Override
            public void onCallback(Object value) {

            }

            @Override
            public void exceptionHandler(String message) {
                cb.exceptionHandler("Error: Could not load services from database");
            }
        });
    }

    public static void createService(Service service) {
        service.dbStore(null);
    }

    public static void editService(String oldService, String newService, String newRole) {
        Service.dbEdit(oldService, newService, newRole);
    }

    public static void deleteService(String service) {
        Service.dbDelete(service);
    }
}
