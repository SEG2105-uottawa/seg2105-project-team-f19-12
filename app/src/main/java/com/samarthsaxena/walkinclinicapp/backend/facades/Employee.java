package com.samarthsaxena.walkinclinicapp.backend.facades;

import com.samarthsaxena.walkinclinicapp.backend.models.Profile;
import com.samarthsaxena.walkinclinicapp.backend.models.Service;
import com.samarthsaxena.walkinclinicapp.backend.models.User;
import com.samarthsaxena.walkinclinicapp.backend.models.UserService;

import java.util.ArrayList;

public class Employee {

    private Employee() {

    }

    public static void createProfile(Profile profile) {
        profile.dbStore(null);
    }

    public static void editProfile(String user, String key, String value) {
        Profile.dbEdit(user, key, value);
    }

    public static Profile viewProfile(String user) {
        ArrayList<Profile> profiles = Profile.dbGetAll("user", user, null);
        return profiles.get(0);
    }

    public static void createUserServiceAssociation(String user, String service) {
        UserService association = new UserService(user, service);
    }

    public static ArrayList<Service> viewServicesOfUser(String user) {
        ArrayList<Service> services = new ArrayList<>();

        return services;
    }

    public static void deleteServiceOfUser(String user, String service) {

    }
}
