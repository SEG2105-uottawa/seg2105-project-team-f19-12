package com.samarthsaxena.walkinclinicapp.backend.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;

import java.util.ArrayList;
import java.util.Iterator;

public class Profile {

    private static final FirebaseDatabase db = FirebaseDatabase.getInstance();

    // Name of fields stored in database
    private static final String PROFILE_STRING                      = "Profile";
    private static final String PROFILE_USER_STRING                 = "user";
    private static final String PROFILE_ADDRESS_STRING              = "address";
    private static final String PROFILE_PHONE_NUM_STRING            = "phoneNumber";
    private static final String PROFILE_CLINIC_STRING               = "clinic";
    private static final String PROFILE_INSURANCE_STRING            = "insuranceType";
    private static final String PROFILE_PAYMENT_STRING              = "paymentMethod";
    private static final String PROFILE_TIME_STRING                 = "workingTime";

    private String user;
    private String address;
    private int phoneNumber;
    private String clinic;
    private String insuranceType;
    private String paymentMethod;
    private ArrayList<String> workingTime;

    public String getUser() {
        return user;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getClinic() {
        return clinic;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public ArrayList<String> getWorkingTime() {
        return workingTime;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setWorkingTime(ArrayList<String> workingTime) {
        this.workingTime = workingTime;
    }

    public Profile() {
        this.user = null;
        this.address = null;
        this.phoneNumber = 0;
        this.clinic = null;
        this.insuranceType = null;
        this.paymentMethod = null;
        this.workingTime = null;
    }

    public Profile(String user,
                   String address,
                   int phoneNumber,
                   String clinic,
                   String insuranceType,
                   String paymentMethod,
                   ArrayList<String> workingTime) {
        this.user = user;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.clinic = clinic;
        this.insuranceType = insuranceType;
        this.paymentMethod = paymentMethod;
        this.workingTime = workingTime;
    }

    public void dbStore(final MyCallback cb) throws RuntimeException {

        DatabaseReference profileRef = db.getReference().child(PROFILE_STRING).push();
        profileRef.child(PROFILE_USER_STRING)               .setValue(user);
        profileRef.child(PROFILE_ADDRESS_STRING)            .setValue(address);
        profileRef.child(PROFILE_PHONE_NUM_STRING)          .setValue(phoneNumber);
        profileRef.child(PROFILE_CLINIC_STRING)             .setValue(clinic);
        profileRef.child(PROFILE_INSURANCE_STRING)          .setValue(insuranceType);
        profileRef.child(PROFILE_PAYMENT_STRING)            .setValue(paymentMethod);
        profileRef.child(PROFILE_TIME_STRING)               .setValue(workingTime);

        if (cb != null) {
            cb.onCallback(Profile.this);
        }
    }

    public static ArrayList<Profile> dbGetAll(final String param, final String value, final MyCallback cb) {

        final ArrayList<Profile> profiles = new ArrayList<Profile>();


        // Check param type is an existing user field
        if (!param.equals(PROFILE_USER_STRING) &&
                !param.equals(PROFILE_ADDRESS_STRING) &&
                !param.equals(PROFILE_PHONE_NUM_STRING) &&
                !param.equals(PROFILE_CLINIC_STRING) &&
                !param.equals(PROFILE_INSURANCE_STRING) &&
                !param.equals(PROFILE_PAYMENT_STRING) &&
                !param.equals(PROFILE_TIME_STRING) &&
                !param.equals("all")) {
            cb.exceptionHandler("Invalid user parameter");
        }

        DatabaseReference ref = db.getReference().child(PROFILE_STRING);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    if ((userSnapshot.child(param).getValue() != null &&
                            userSnapshot.child(param).getValue().toString().equals(value)) ||
                            param.equals("all")) {

                        String user = "";
                        String address = "";
                        int phoneNumber = 0;
                        String clinic = "";
                        String insuranceType = "";
                        String paymentMethod = "";
                        ArrayList<String> workingTime = new ArrayList<>();

                        Profile profile;

                        try {
                            user = userSnapshot.child(PROFILE_USER_STRING).getValue().toString();
                            address = userSnapshot.child(PROFILE_ADDRESS_STRING).getValue().toString();
                            phoneNumber = Integer.parseInt(userSnapshot.child(PROFILE_PHONE_NUM_STRING).getValue().toString());
                            clinic = userSnapshot.child(PROFILE_CLINIC_STRING).getValue().toString();
                            insuranceType = userSnapshot.child(PROFILE_INSURANCE_STRING).getValue().toString();
                            paymentMethod = userSnapshot.child(PROFILE_PAYMENT_STRING).getValue().toString();
                            Iterator<DataSnapshot> iter = userSnapshot.child(PROFILE_TIME_STRING).getChildren().iterator();
                            while (iter.hasNext()) {
                                workingTime.add(iter.next().toString());
                            }
                        } catch (NullPointerException e) {
                            break;
                        }

                        profile = new Profile(user, address, phoneNumber, clinic, insuranceType, paymentMethod, workingTime);
                        profiles.add(profile);
                    }
                }
                if (cb != null) {
                    cb.onCallback(profiles);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                cb.exceptionHandler(databaseError.getMessage());
            }
        });

        return profiles;
    }




    public static void dbEdit(final String user, final String key, final String value) {

        if (!key.equals(PROFILE_USER_STRING) &&
                !key.equals(PROFILE_ADDRESS_STRING) &&
                !key.equals(PROFILE_PHONE_NUM_STRING) &&
                !key.equals(PROFILE_CLINIC_STRING) &&
                !key.equals(PROFILE_INSURANCE_STRING) &&
                !key.equals(PROFILE_PAYMENT_STRING) &&
                !key.equals(PROFILE_TIME_STRING)
        ) {
            return;
        }

        Query dbQuery = db.getReference().child(PROFILE_STRING).orderByChild(PROFILE_USER_STRING).equalTo(user);

        dbQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot servSnapshot: dataSnapshot.getChildren()) {
                    if (servSnapshot.getRef().child(PROFILE_USER_STRING).toString().equals(user)) {
                        servSnapshot.getRef().child(key).setValue(value);
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

}
