package com.samarthsaxena.walkinclinicapp.backend.models;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.ArrayList;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;

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

    // Instance to database
    private static final FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static final FirebaseAuth fbAuth = FirebaseAuth.getInstance();

    // Protected since only an instance of user subclass is allowed to exist
    // Protected guarantees a type of user is specified in the subclasses ctors
    protected User(String email, String username, String hashedPassword) {

        this.email = email;
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    // Store user object in database
    protected void dbStore() throws RuntimeException {

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
                        } else {
                            throw new RuntimeException(task.getException().getMessage());
                        }
                    }
                });
    }

    // TODO
    // Find all Users in db where <param> == <value>
    public static ArrayList<User> dbGetAll(final String param, String value) {

        ArrayList<User> users = new ArrayList<User>();

        // Check param type is an existing user field
        if  (
                !param.equals(USER_EMAIL_STRING) &&
                !param.equals(USER_USERNAME_STRING) &&
                !param.equals(USER_HASH_PASSWORD_STRING) &&
                !param.equals(USER_TYPE_STRING)
            ) {
            return users; // TODO Maybe throw an exception?
        }

        return users;
    }


}
