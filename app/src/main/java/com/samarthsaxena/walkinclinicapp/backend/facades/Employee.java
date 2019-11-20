package com.samarthsaxena.walkinclinicapp.backend.facades;

import com.samarthsaxena.walkinclinicapp.backend.models.Profile;

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
}
