package com.samarthsaxena.walkinclinicapp.backend.models;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;

import java.util.ArrayList;

public class User {

    // Name of fields stored in database
    public static final String USER_STRING                    = "Users";
    public static final String USER_EMAIL_STRING              = "email";
    public static final String USER_USERNAME_STRING           = "username";
    public static final String USER_HASH_PASSWORD_STRING      = "hash password";
    public static final String USER_TYPE_STRING               = "type";

    // User fields
    protected String email;
    protected String username;
    protected String hashedPassword;
    protected String type;

    private static final FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static final FirebaseAuth fbAuth = FirebaseAuth.getInstance();

    public User() {
        this.email = null;
        this.username = null;
        this.hashedPassword = null;
    }

    public User(String email, String username, String hashedPassword, String type) {

        this.email = email;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public String getEmail() { return email; }
    public String getType() { return type; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String newHash) { hashedPassword = newHash; }
    public void setEmail(String email) { this.email = email; }
    public void setType(String type) { this.type = type; }

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

                            if (cb != null) {
                                cb.onCallback(User.this);
                            }
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
                !param.equals(USER_TYPE_STRING) &&
                !param.equals("all")
            ) {
            cb.exceptionHandler("Invalid user parameter");
        }

        DatabaseReference ref = db.getReference().child(USER_STRING);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    if ((userSnapshot.child(param).getValue() != null &&
                         userSnapshot.child(param).getValue().toString().equals(value)) ||
                         param.equals("all")) {

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

                        user = new User(email, username, password, type);
                        users.add(user);
                    }
                }
                if (cb != null) {
                    cb.onCallback(users);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                cb.exceptionHandler(databaseError.getMessage());
            }
        });

        return users;
    }

    public static void dbDelete(String username) {

        Query dbQuery = db.getReference().child(USER_STRING).orderByChild(USER_USERNAME_STRING).equalTo(username);

        dbQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    userSnapshot.getRef().removeValue();
                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
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