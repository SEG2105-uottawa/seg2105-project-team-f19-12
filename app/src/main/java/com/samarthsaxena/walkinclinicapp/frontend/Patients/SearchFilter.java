package com.samarthsaxena.walkinclinicapp.frontend.Patients;


import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.facades.Patient;
import com.samarthsaxena.walkinclinicapp.backend.models.Profile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SearchFilter {
    private String param;
    private int day;
    private ArrayList<Profile> profiles;

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
    }

    public void setParam(String param) {
        this.param = param;
        this.day=0;
    }

    public void setParam(String param, int day) {
        this.param = param;
        this.day=day;
    }

    public ArrayList<Profile> filterMain(){
        ArrayList<Profile> sortedList= new ArrayList<Profile>();

        switch (this.param){
            case "address":sortedList=sortedAddress();
                break;
//            case "service":sortedList=sortedService();
//            break;
            case "workingHours":sortedList=sortedWorkingHours();
                break;
        }
        return sortedList;
    }

    private ArrayList<Profile> sortedAddress(){
        ArrayList<Profile> profilesSortedByAddress= new ArrayList<Profile>();
        ArrayList<String> sortedAddresses= new ArrayList<String>();
        Profile temp;

        for(int i=0;i<profiles.size();i++){
            temp=profiles.get(i);
            sortedAddresses.add(temp.getAddress());
        }
        Collections.sort(sortedAddresses, new Comparator<String>() {
            @Override
            public int compare(String S1, String S2) {
                return S1.compareToIgnoreCase(S2);
            }
        });

        for(int i=0;i<sortedAddresses.size();i++){
            for(int j=0;j<sortedAddresses.size();j++){
                if(sortedAddresses.get(i)==profiles.get(j).getAddress()){
                    profilesSortedByAddress.add(profiles.get(j));
                }
            }
        }
        return profilesSortedByAddress;
    }

//    private ArrayList<Profile> sortedService(){
//        ArrayList<Profile> profilesSortedByService= new ArrayList<Profile>();
//        ArrayList<String> sortedServices= new ArrayList<String>();
//        Profile temp;
//
//        for(int i=0;i<profiles.size();i++){
//            temp=profiles.get(i);
//            sortedServices.add(temp.getService());
//        }
//        Collections.sort(sortedServices, new Comparator<String>() {
//            @Override
//            public int compare(String S1, String S2) {
//                return S1.compareToIgnoreCase(S2);
//            }
//        });
//
//        for(int i=0;i<sortedServices.size();i++){
//            for(int j=0;j<sortedServices.size();j++){
//                if(sortedServices.get(i)==profiles.get(j).getService()){
//                    profilesSortedByService.add(profiles.get(j));
//                }
//            }
//        }
//        return profilesSortedByService;
//    }

    private ArrayList<Profile> sortedWorkingHours() {
        ArrayList<Profile> profilesSortedByWH= new ArrayList<Profile>();
        ArrayList<String> sortedWorkingHours= new ArrayList<String>();
        Profile temp;

        for(int i=0;i<profiles.size();i++){
            temp=profiles.get(i);
            sortedWorkingHours.add(temp.getWorkingTime().get(day).get(0));
        }
        Collections.sort(sortedWorkingHours, new Comparator<String>() {
            @Override
            public int compare(String S1, String S2) {
                return S1.compareToIgnoreCase(S2);
            }
        });

        for(int i=0;i<sortedWorkingHours.size();i++){
            for(int j=0;j<sortedWorkingHours.size();j++){
                if(sortedWorkingHours.get(i)==profiles.get(j).getAddress()){
                    profilesSortedByWH.add(profiles.get(j));
                }
            }
        }
        return profilesSortedByWH;
    }

}
