package com.samarthsaxena.walkinclinicapp.backend.facades;

import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.models.Profile;
import com.samarthsaxena.walkinclinicapp.backend.models.UserService;
import com.samarthsaxena.walkinclinicapp.backend.facades.Employee;

import java.util.ArrayList;

public class Patient {

    public static void getClinics(final String userInput, final int mode, final MyCallback cb) {

        Profile.dbGetAll("all", null, new MyCallback() {
            @Override
            public void onCallback(Object value) {
                final ArrayList<Profile> allClinics = (ArrayList<Profile>) value;
                final ArrayList<Profile> results = new ArrayList<>();

                if (mode == 1 || mode == 2) {
                    for (Profile clinic : allClinics) {
                        // Search address
                        if (clinic.getAddress().contains(userInput)) {
                            results.add(clinic);
                        }
                        // Check working hours
                    }
                } else {

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

            }

            @Override
            public void exceptionHandler(String message) {

            }
        });
    }

    public static void scheduleTimeSlot(final String username, final int weekday, final int hour, final MyCallback cb) {
        Profile.dbGetAll(Profile.PROFILE_USER_STRING, username, new MyCallback() {
            @Override
            public void onCallback(Object value) {
                ArrayList<Profile> profiles = (ArrayList<Profile>) value;
                // Check only one employee is returned
                if (profiles.size() != 1) {
                    cb.exceptionHandler("Error: Employee with username <" + username + "> doesn't exist");
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
                // Check waiting time
                Profile.dbGetTimeSlots(username, new MyCallback() {
                    @Override
                    public void onCallback(Object value) {
                        ArrayList<ArrayList<String>> timeslots = (ArrayList<ArrayList<String>>) value;
                        ArrayList<String> hours = timeslots.get(weekday);
                        int waitingPosition = hour;
                        for (String timeslot : hours) {

                        }
                        Employee.createUserServiceAssociation(username, "");
                    }


                    @Override
                    public void exceptionHandler(String message) {
                        cb.exceptionHandler(message);
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
