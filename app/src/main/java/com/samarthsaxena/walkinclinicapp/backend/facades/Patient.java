package com.samarthsaxena.walkinclinicapp.backend.facades;

import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.models.Profile;
import com.samarthsaxena.walkinclinicapp.backend.models.UserService;

import java.util.ArrayList;

public class Patient {

    public void getClinics(final String userInput, final MyCallback cb) {

        Profile.dbGetAll("all", null, new MyCallback() {
            @Override
            public void onCallback(Object value) {
                final ArrayList<Profile> allClinics = (ArrayList<Profile>) value;
                final ArrayList<Profile> results = new ArrayList<>();
                for (Profile clinic : allClinics) {

                    // Search address
                    if (clinic.getAddress().contains(userInput)) {
                        results.add(clinic);
                    }

                    // Check working hours


                }

                // Search services
                UserService.dbGetAll("all", null, new MyCallback() {
                    @Override
                    public void onCallback(Object value) {
                        final ArrayList<UserService> userServices = (ArrayList<UserService>) value;
                        for (UserService n : userServices) {
                            if (userInput.contains(n.getService())) {
                                for (Profile c : allClinics) {
                                    if (c.getUser().equals(n.getUser())) {
                                        results.add(c);
                                    }
                                }
                            }
                        }
                        cb.onCallback(results);
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

    public void scheduleTimeslot(int hour) {

    }
}
