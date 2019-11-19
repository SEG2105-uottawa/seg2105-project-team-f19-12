package com.samarthsaxena.walkinclinicapp.backend.facades;

import com.samarthsaxena.walkinclinicapp.backend.models.Profile;

public class Employee {

    private Employee() {

    }

    public static void createProfile(Profile profile) {
        profile.dbStore(null);
    }

    public static void editProfile(String user, String key, String value) {
        Profile.dbEdit(user, key, value);
    }
}
