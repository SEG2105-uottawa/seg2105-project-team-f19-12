package com.samarthsaxena.walkinclinicapp.backend.facades;

import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.models.Profile;

import java.util.ArrayList;

public class Patient {

    public static void getAllClinics(final MyCallback cb) {

        Profile.dbGetAll("all", null, cb);
    }

    public static void scheduleTimeSlot(final String patient, final String clinic, final int weekday, final int hour, final MyCallback cb) {
        Profile.dbGetAll(Profile.PROFILE_USER_STRING, clinic, new MyCallback() {
            @Override
            public void onCallback(Object value) {
                ArrayList<Profile> profiles = (ArrayList<Profile>) value;
                // Check only one employee is returned
                if (profiles.size() != 1) {
                    cb.exceptionHandler("Error: Employee with username <" + clinic + "> doesn't exist");
                    return;
                }
                Profile profile = profiles.get(0);
                // Check time chosen is valid
                int profileHour1 = Integer.parseInt(profile.getWorkingTime().get(weekday).get(0));
                int profileHour2 = Integer.parseInt(profile.getWorkingTime().get(weekday).get(1));
                if (hour < profileHour1 || hour >= profileHour2) {
                    cb.exceptionHandler("Error: Requested time outside clinic working hours");
                    return;
                }
                Profile.dbFillTimeSlot(patient, clinic, weekday, hour, new MyCallback() {
                    @Override
                    public void onCallback(Object value) {
                        cb.onCallback(value);
                    }

                    @Override
                    public void exceptionHandler(String message) {

                    }
                });
            }

            @Override
            public void exceptionHandler(String message) {

            }
        });
    }

    public static void rateService(final String user, final int score) {

    }
}
