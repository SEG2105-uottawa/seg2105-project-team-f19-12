package com.samarthsaxena.walkinclinicapp.backend.facades;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.models.Profile;
import com.samarthsaxena.walkinclinicapp.backend.models.Service;
import com.samarthsaxena.walkinclinicapp.backend.models.User;
import com.samarthsaxena.walkinclinicapp.backend.models.UserService;

import java.util.ArrayList;
import java.util.Iterator;

public class Employee {

    private Employee() {

    }

    public static void createProfile(Profile profile) {
        profile.dbStore(null);
    }

    public static void editProfile(String user, String key, Object value) {
        Profile.dbEdit(user, key, value);
    }

    public static void viewProfile(String user, final MyCallback cb) {
        ArrayList<Profile> profiles = Profile.dbGetAll(Profile.PROFILE_USER_STRING, user, new MyCallback() {
            @Override
            public void onCallback(Object value) {
                ArrayList<Profile> profiles = (ArrayList<Profile>) value;
                cb.onCallback(profiles.get(0));
                return;
            }

            @Override
            public void exceptionHandler(String message) {

            }
        });
    }

    public static void createUserServiceAssociation(String user, String service) {
        UserService association = new UserService(user, service);
        association.dbStore(null);
    }

    public static ArrayList<Service> viewServicesOfUser(String user) {

        final ArrayList<Service> servicesOfUser = new ArrayList<>();

        UserService.dbGetAll(UserService.USERSERVICE_USER_STRING, user, new MyCallback() {
            @Override
            public void onCallback(Object value) {
                ArrayList<UserService> servicesOfUserAssociations = (ArrayList<UserService>) value;
                Iterator<UserService> iterator = servicesOfUserAssociations.iterator();
                recuGetServices(servicesOfUser, iterator);
            }

            @Override
            public void exceptionHandler(String message) {

            }
        });

        return servicesOfUser;
    }

    public static void deleteServiceOfUser(String user, String service) {


    }

    private static void recuGetServices(final ArrayList<Service> services, final Iterator<UserService> iterator) {

        if (iterator.hasNext()) {
            Service.dbGetAll(Service.SERVICE_OFFERED_STRING, iterator.next().getService(), new MyCallback() {
                @Override
                public void onCallback(Object value) {
                    ArrayList<Service> serviceList = (ArrayList<Service>) value;
                    services.add(serviceList.get(0));
                    recuGetServices(services, iterator);
                }

                @Override
                public void exceptionHandler(String message) {

                }
            });
        }

    }
}
