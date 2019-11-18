package com.samarthsaxena.walkinclinicapp.backend.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;

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

    private String user;
    private String address;
    private int phoneNumber;
    private String clinic;
    private String insuranceType;
    private String paymentMethod;

    public Profile() {
        this.user = null;
        this.address = null;
        this.phoneNumber = 0;
        this.clinic = null;
        this.insuranceType = null;
        this.paymentMethod = null;
    }

    public Profile(String user, String address, int phoneNumber, String clinic, String insuranceType, String paymentMethod) {
        this.user = user;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.clinic = clinic;
        this.insuranceType = insuranceType;
        this.paymentMethod = paymentMethod;
    }

    public void dbStore(final MyCallback cb) throws RuntimeException {

        DatabaseReference serviceRef = db.getReference().child(PROFILE_STRING).push();
        serviceRef.child(PROFILE_USER_STRING)               .setValue(user);
        serviceRef.child(PROFILE_ADDRESS_STRING)            .setValue(address);
        serviceRef.child(PROFILE_PHONE_NUM_STRING)          .setValue(phoneNumber);
        serviceRef.child(PROFILE_CLINIC_STRING)             .setValue(clinic);
        serviceRef.child(PROFILE_INSURANCE_STRING)          .setValue(insuranceType);
        serviceRef.child(PROFILE_PAYMENT_STRING)            .setValue(paymentMethod);
        if (cb != null) {
            cb.onCallback(Profile.this);
        }
    }

    public static void dbEdit(final String user, final String key, final String value) {

        if (!key.equals(PROFILE_USER_STRING) &&
                !key.equals(PROFILE_ADDRESS_STRING) &&
                !key.equals(PROFILE_PHONE_NUM_STRING) &&
                !key.equals(PROFILE_CLINIC_STRING) &&
                !key.equals(PROFILE_INSURANCE_STRING) &&
                !key.equals(PROFILE_PAYMENT_STRING)) {
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
