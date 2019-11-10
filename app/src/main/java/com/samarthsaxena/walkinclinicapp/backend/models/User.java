package com.samarthsaxena.walkinclinicapp.backend.models;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;

public class User {

    // Name of fields stored in database
    private static final String USER_STRING                    = "Users";
    private static final String USER_EMAIL_STRING              = "email";
    private static final String USER_USERNAME_STRING           = "username";
    private static final String USER_HASH_PASSWORD_STRING      = "hash password";
    private static final String USER_TYPE_STRING               = "type";

    // User fields
    protected String email;
    protected String username;
    protected String hashedPassword;
    protected String type;

    private static final FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static final FirebaseAuth fbAuth = FirebaseAuth.getInstance();

    // Protected since only an instance of user subclass is allowed to exist
    // Protected guarantees a type of user is specified in the subclasses ctors
    public User(String email, String username, String hashedPassword) {

        this.email = email;
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getEmail() { return email; }

    public String getType() { return type; }

    // Store user object in database
    public void dbStore(final MyCallback cb) throws RuntimeException {

        fbAuth.createUserWithEmailAndPassword(email.trim(), hashedPassword.trim())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DatabaseReference user = db.getReference().child(USER_STRING).push();
                            user.child(USER_EMAIL_STRING)           .setValue(email);
                            user.child(USER_USERNAME_STRING)        .setValue(username);
                            user.child(USER_HASH_PASSWORD_STRING)   .setValue(hashedPassword);
                            user.child(USER_TYPE_STRING)            .setValue(type);

                            cb.onCallback(User.this);
                        } else {
                            cb.exceptionHandler(task.getException().getMessage());
                        }
                    }
                });
    }

    // Find all Users in db where <param> == <value>
    // The callback method is required due to the async nature of data retrieval
    public static ArrayList<User> dbGetAll(final String param, final String value, final MyCallback cb) {

        final ArrayList<User> users = new ArrayList<User>();


        // Check param type is an existing user field
        if  (
                !param.equals(USER_EMAIL_STRING) &&
                !param.equals(USER_USERNAME_STRING) &&
                !param.equals(USER_HASH_PASSWORD_STRING) &&
                !param.equals(USER_TYPE_STRING)
            ) {
            cb.exceptionHandler("Invalid user parameter");
        }

        DatabaseReference ref = db.getReference().child(USER_STRING);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    if (userSnapshot.child(param).getValue() != null &&
                            userSnapshot.child(param).getValue().toString().equals(value)) {

                        String type = "";
                        String email = "";
                        String username = "";
                        String password = "";

                        User user;
                        try {
                            type = userSnapshot.child(USER_TYPE_STRING).getValue().toString();
                            email = userSnapshot.child(USER_EMAIL_STRING).getValue().toString();
                            username = userSnapshot.child(USER_USERNAME_STRING).getValue().toString();
                            password = userSnapshot.child(USER_HASH_PASSWORD_STRING).getValue().toString();
                        } catch (NullPointerException e) {
                            break;
                        }


                        if (type.equals("patient")) {
                            user = new Patient(email, username, password);

                        } else if (type.equals("employee")) {
                            user = new Employee(email, username, password);

                        } else {
                            user = new Admin(email, username, password);
                        }
                        users.add(user);
                    }
                }
                cb.onCallback(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                cb.exceptionHandler(databaseError.getMessage());
            }
        });

        return users;
    }


    @Override
    public String toString() {
        String output =
                "<username: " + username + ", " +
                "email: " + email + ", " +
                "type: " + type + ">";
        return output;
    }

}