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
    private static final String weekday[] = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    // Name of fields stored in database
    public static final String PROFILE_STRING                      = "Profile";
    public static final String PROFILE_USER_STRING                 = "user";
    public static final String PROFILE_ADDRESS_STRING              = "address";
    public static final String PROFILE_PHONE_NUM_STRING            = "phoneNumber";
    public static final String PROFILE_CLINIC_STRING               = "clinic";
    public static final String PROFILE_INSURANCE_STRING            = "insuranceType";
    public static final String PROFILE_PAYMENT_STRING              = "paymentMethod";
    public static final String PROFILE_TIME_STRING                 = "workingTime";
    public static final String PROFILE_TIME_SLOT_STRING            = "timeslots";

    private String user;
    private String address;
    private int phoneNumber;
    private String clinic;
    private String insuranceType;
    private String paymentMethod;
    private ArrayList<ArrayList<String>> workingTime;

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

    public ArrayList<ArrayList<String>> getWorkingTime() {
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

    public void setWorkingTime(ArrayList<ArrayList<String>> workingTime) {
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
                   String paymentMethod) {
        this.user = user;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.clinic = clinic;
        this.insuranceType = insuranceType;
        this.paymentMethod = paymentMethod;
        this.workingTime = new ArrayList<>();
    }

    public void dbStore(final MyCallback cb) throws RuntimeException {

        DatabaseReference profileRef = db.getReference().child(PROFILE_STRING).push();
        profileRef.child(PROFILE_USER_STRING)               .setValue(user);
        profileRef.child(PROFILE_ADDRESS_STRING)            .setValue(address);
        profileRef.child(PROFILE_PHONE_NUM_STRING)          .setValue(phoneNumber);
        profileRef.child(PROFILE_CLINIC_STRING)             .setValue(clinic);
        profileRef.child(PROFILE_INSURANCE_STRING)          .setValue(insuranceType);
        profileRef.child(PROFILE_PAYMENT_STRING)            .setValue(paymentMethod);

        // Generate empty working time
        ArrayList<ArrayList<String>> workingHours = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                temp.add("0");
            }
            workingHours.add(temp);
        }
        dbStoreWorkingTime(this.user, workingHours);

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
                        ArrayList<ArrayList<String>> workingTime = new ArrayList<>();

                        try {
                            user = userSnapshot.child(PROFILE_USER_STRING).getValue().toString();
                            address = userSnapshot.child(PROFILE_ADDRESS_STRING).getValue().toString();
                            phoneNumber = Integer.parseInt(userSnapshot.child(PROFILE_PHONE_NUM_STRING).getValue().toString());
                            clinic = userSnapshot.child(PROFILE_CLINIC_STRING).getValue().toString();
                            insuranceType = userSnapshot.child(PROFILE_INSURANCE_STRING).getValue().toString();
                            paymentMethod = userSnapshot.child(PROFILE_PAYMENT_STRING).getValue().toString();
                            for (int i = 0; i < 7; i++) {
                                Iterator<DataSnapshot> iter = userSnapshot.child(PROFILE_TIME_STRING).child(Integer.toString(i)).getChildren().iterator();
                                ArrayList<String> temp = new ArrayList<>();
                                while (iter.hasNext()) {
                                    temp.add(iter.next().getValue().toString());
                                }
                                workingTime.add(temp);
                            }

                        } catch (NullPointerException e) {
                            break;
                        }

                        final Profile profile = new Profile(user, address, phoneNumber, clinic, insuranceType, paymentMethod);
                        profile.setWorkingTime(workingTime);

                        /*
                        UserService.dbGetAll(UserService.USERSERVICE_USER_STRING, user, new MyCallback() {
                            @Override
                            public void onCallback(Object value) {
                                ArrayList<UserService> userServices = (ArrayList<UserService>) value;
                                ArrayList<Service> services = new ArrayList<>();
                                for (UserService n : userServices) {
                                    Service.dbGetAll(Service.SERVICE_OFFERED_STRING, n.getService(), new MyCallback() {
                                        @Override
                                        public void onCallback(Object value) {
                                            ArrayList<Service> servs = (ArrayList<Service>) value;

                                        }

                                        @Override
                                        public void exceptionHandler(String message) {

                                        }
                                    })
                                }

                            }

                            @Override
                            public void exceptionHandler(String message) {

                            }
                        });
                        */
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

    public static void dbEdit(final String user, final String key, final Object value) {

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

    public static void dbStoreWorkingTime(final String user, final ArrayList<ArrayList<String>> workingTime) {

        Query dbQuery = db.getReference().child(PROFILE_STRING).orderByChild(PROFILE_USER_STRING).equalTo(user);

        dbQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot profile: dataSnapshot.getChildren()) {
                    if (profile.child(PROFILE_USER_STRING).getValue().toString().equals(user)) {
                        for (int i = 0; i < 7; i++) {
                            ArrayList<String> interval = new ArrayList<>();
                            interval.add(workingTime.get(0).get(i));
                            interval.add(workingTime.get(1).get(i));
                            profile.getRef().child(PROFILE_TIME_STRING).child(Integer.toString(i)).setValue(interval);
                        }
                        generateTimeslots(workingTime, profile.getRef());
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void dbGetTimeSlots(final String user, final MyCallback cb) {

        Query dbQuery = db.getReference().child(PROFILE_STRING).orderByChild(PROFILE_USER_STRING).equalTo(user);

        dbQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<ArrayList<String>> timeslots = new ArrayList<>();

                for (DataSnapshot profile : dataSnapshot.getChildren()) {
                    if (profile.child(PROFILE_USER_STRING).getValue().toString().equals(user)) {
                        for (int i = 0; i < 7; i++) {
                            ArrayList<String> weekdayHours = new ArrayList<>();
                            Iterator<DataSnapshot> iterator = profile.child(PROFILE_TIME_STRING).child(Integer.toString(i)).getChildren().iterator();
                            while (iterator.hasNext()) {
                                weekdayHours.add(iterator.next().getValue().toString());
                            }
                            timeslots.add(weekdayHours);
                        }
                    }
                }

                cb.onCallback(timeslots);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                cb.exceptionHandler(databaseError.getMessage());
            }
        });

    }

    public static void dbFillTimeSlot(final String patient, final String clinic, final int weekday, final int hour, final MyCallback cb) {
        Query dbQuery = db.getReference().child(PROFILE_STRING).orderByChild(PROFILE_USER_STRING).equalTo(clinic);
        dbQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot profile: dataSnapshot.getChildren()) {
                    if (profile.child(PROFILE_USER_STRING).getValue().toString().equals(clinic)) {
                        DatabaseReference dayRef = profile.getRef().child(PROFILE_TIME_SLOT_STRING).child(Integer.toString(weekday));
                        // get start and end time boundaries
                        Iterator<DataSnapshot> iter1 = profile.child(PROFILE_TIME_STRING).child(Integer.toString(weekday)).getChildren().iterator();
                        int startTime = Integer.parseInt(iter1.next().getValue().toString());
                        int endTime = Integer.parseInt(iter1.next().getValue().toString());
                        int length = endTime - startTime;
                        Iterator<DataSnapshot> iter2 = profile.child(PROFILE_TIME_SLOT_STRING).child(Integer.toString(weekday)).getChildren().iterator();
                        while (iter2.hasNext()) {
                            DataSnapshot snap = iter2.next();
                            String timeSlot = snap.getKey().toString();
                            int timeParams[] = parseTimeRange(timeSlot);
                            if (timeParams[0] == hour) {
                                for (int i = 0; !snap.getValue().toString().equals("") && i < 4; i++) {
                                    snap = iter2.next();
                                }
                                snap.getRef().setValue(patient);
                                cb.onCallback(snap.getKey().toString());
                                return;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private static void generateTimeslots(ArrayList<ArrayList<String>> workingTime, DatabaseReference ref) {
        for (int i = 0; i < 7; i++) {
            DatabaseReference slotRef = ref.child(PROFILE_TIME_SLOT_STRING).child(Integer.toString(i));
            // Divide time slot into 15 minutes
            int timeLimitBegin = Integer.parseInt(workingTime.get(0).get(i));
            int timeLimitEnd = Integer.parseInt(workingTime.get(1).get(i));
            int numOf15minSlots = (timeLimitEnd - timeLimitBegin) * 4;
            for (int j = 0; j < numOf15minSlots; j++) {
                int hour1 = timeLimitBegin + (int)(j / 4);
                int hour2 = timeLimitBegin + (int)((j + 1) / 4);
                int min1 = 15*(j % 4);
                int min2 = 15*((j + 1) % 4);
                String min1Str = (min1 == 0) ? "00" : Integer.toString(min1);
                String min2Str = (min2 == 0) ? "00" : Integer.toString(min2);
                String parsedTime = (hour1 + ":" + min1Str + " - " + hour2 + ":" + min2Str);
                slotRef.child(parsedTime).setValue("");
            }

        }
    }

    private static int[] parseTimeRange(String timeStr) {
        int[] out = {0, 0, 0, 0};
        if (timeStr.charAt(1) == ':') {
            out[0] = Character.getNumericValue(timeStr.charAt(0));
            out[1] = Integer.parseInt(timeStr.substring(2, 4));
            if (timeStr.charAt(8) == ':') {
                out[2] = Character.getNumericValue(timeStr.charAt(7));
                out[3] = Integer.parseInt(timeStr.substring(9, 11));
            } else {
                out[2] = Integer.parseInt(timeStr.substring(7, 9));
                out[3] = Integer.parseInt(timeStr.substring(10, 12));
            }
        } else {
            out[0] = Integer.parseInt(timeStr.substring(0, 2));
            out[1] = Integer.parseInt(timeStr.substring(3, 5));
            if (timeStr.charAt(9) == ':') {
                out[2] = Character.getNumericValue(timeStr.charAt(8));
                out[3] = Integer.parseInt(timeStr.substring(10, 12));
            } else {
                out[2] = Integer.parseInt(timeStr.substring(8, 10));
                out[3] = Integer.parseInt(timeStr.substring(11, 13));
            }
        }

        return out;
    }

}

