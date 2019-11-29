package com.samarthsaxena.walkinclinicapp.frontend.Patients;


import android.content.Context;
import android.widget.Toast;

import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.facades.Patient;
import com.samarthsaxena.walkinclinicapp.backend.models.Profile;
import com.samarthsaxena.walkinclinicapp.backend.models.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SearchFilter {
    private String param;
    private String search;
    private int day;
    private ArrayList<Profile> profiles;
    private ArrayList<UserService> userServiceAssociations;
    private Context context;

    public  SearchFilter(){
        Patient.getAllClinics(new MyCallback() {
            @Override
            public void onCallback(Object value) {
                profiles = (ArrayList<Profile> ) value;
            }

            @Override
            public void exceptionHandler(String message) {

            }
        });
        UserService.dbGetAll("all", null, new MyCallback() {
            @Override
            public void onCallback(Object value) {
                userServiceAssociations = (ArrayList<UserService>) value;
            }

            @Override
            public void exceptionHandler(String message) {

            }
        });
    }

    public void setParam(String param, String search) {
        this.param = param;
        this.search=search;
        this.day=0;
    }

    public void setParam(String param, String search, int day) {
        this.param = param;
        this.search=search;
        this.day=day;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Profile> filterMain(){
        ArrayList<Profile> list= new ArrayList<Profile>();

        switch (this.param){
           case "address":list=sortedAddress();
           break;
           case "service":list=sortedService();
           break;
           case "workingHours":list=sortedWH();
           break;
        }
        return list;
    }

    public ArrayList<Profile> sortedAddress(){
        ArrayList<Profile> profilesSortedByAddress= new ArrayList<Profile>();
        ArrayList<String> sorted= new ArrayList<String>();
        Profile temp;

        for(int i=0;i<profiles.size();i++){
            temp=profiles.get(i);
            if(profiles.get(i).getAddress().contains(search)){
                sorted.add(temp.getAddress());
            }

            Collections.sort(sorted, new Comparator<String>() {
                @Override
                public int compare(String S1, String S2) {
                    return S1.compareToIgnoreCase(S2);
                }
            });
        }

        for(int i=0;i<sorted.size();i++){
            for(int j=0;j<profiles.size();j++){
                if(sorted.get(i).equals(profiles.get(j).getAddress())){
                    profilesSortedByAddress.add(profiles.get(j));
                }
            }
        }

        return profilesSortedByAddress;
    }

    private ArrayList<Profile> sortedService() {
        ArrayList<Profile> profilesSortedByService= new ArrayList<Profile>();
        ArrayList<String> sortedNames= new ArrayList<String>();
        Profile temp;

        for(int i=0;i<profiles.size();i++){
              temp=profiles.get(i);
              for(int j=0;j<userServiceAssociations.size();j++){
                  if(userServiceAssociations.get(j).getService().contains(search)&&temp.getUser().equals(userServiceAssociations.get(j).getUser())){
                      sortedNames.add(temp.getClinic());
                  }
              }
        }

        Collections.sort(sortedNames, new Comparator<String>() {
            @Override
            public int compare(String S1, String S2) {
                return S1.compareToIgnoreCase(S2);
            }
        });

        for(int i=0;i<sortedNames.size();i++){
            for(int j=0;j<sortedNames.size();j++){
                if(sortedNames.get(i).equals(profiles.get(j).getClinic())){
                    profilesSortedByService.add(profiles.get(j));
                }
            }
        }
        return profilesSortedByService;
    }

    private ArrayList<Profile> sortedWH() {
        ArrayList<Profile> profilesSortedByWH= new ArrayList<Profile>();
        ArrayList<String> sortedNames= new ArrayList<String>();
        Profile temp;
        try {
            int time = Integer.parseInt(search);
            for (int i = 0; i < profiles.size(); i++) {
                temp = profiles.get(i);
                if (Integer.parseInt(temp.getWorkingTime().get(day).get(0))<=time &&Integer.parseInt(temp.getWorkingTime().get(day).get(1))>=time){
                    sortedNames.add(temp.getClinic());
                }
            }

            Collections.sort(sortedNames, new Comparator<String>() {
                @Override
                public int compare(String S1, String S2) {
                    return S1.compareToIgnoreCase(S2);
                }
            });

            for (int i = 0; i < sortedNames.size(); i++) {
                for (int j = 0; j < sortedNames.size(); j++) {
                    if (sortedNames.get(i).equals(profiles.get(j).getClinic())) {
                        profilesSortedByWH.add(profiles.get(j));
                    }
                }
            }
            return profilesSortedByWH;
        }catch (NumberFormatException E){
            Toast.makeText(context, "Must Be Whole Numbers From 0 to 24", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    public ArrayList<Profile> sortedName(){
        ArrayList<Profile> profilesSortedByName= new ArrayList<Profile>();
        ArrayList<String> sortedNames= new ArrayList<String>();
        Profile temp;

        for(int i=0;i<profiles.size();i++){
            temp=profiles.get(i);
            sortedNames.add(temp.getClinic());
        }
        Collections.sort(sortedNames, new Comparator<String>() {
            @Override
            public int compare(String S1, String S2) {
                return S1.compareToIgnoreCase(S2);
            }
        });

        for(int i=0;i<sortedNames.size();i++){
            for(int j=0;j<sortedNames.size();j++){
                if(sortedNames.get(i).equals(profiles.get(j).getClinic())){
                    profilesSortedByName.add(profiles.get(j));
                }
            }
        }
        return profilesSortedByName;
    }

    public ArrayList<UserService> getUserServiceAssociations() {
        return userServiceAssociations;
    }


    public int getDay() {
        return day;
    }
}
