package com.samarthsaxena.walkinclinicapp.backend.models;

import java.util.ArrayList;
import com.google.firebase.database.*;

public class User {

    // Name of fields stored in database
    private static final String USER_STRING                    = "User";
    private static final String USER_ID_STRING                 = "id";
    private static final String USER_FIRST_NAME_STRING         = "first name";
    private static final String USER_USERNAME_STRING           = "username";
    private static final String USER_HASH_PASSWORD_STRING      = "hash password";
    private static final String USER_TYPE_STRING               = "type";

    // User fields
    protected int id;
    protected String firstName;
    protected String username;
    protected String hashedPassword;
    protected String type;

    // Instance to database
    private static final FirebaseDatabase db = FirebaseDatabase.getInstance();

    // Protected since only an instance of user subclass is allowed to exist
    // Protected guarantees a type of user is specified in the subclasses ctors
    protected User(int id, String firstName, String username, String hashedPassword) {

        this.id = id;
        this.firstName = firstName;
        this.username = username;
        this.hashedPassword = hashedPassword;
        dbStore();
    }

    // Store user object in database
    private void dbStore() {

        DatabaseReference user = this.db.getReference().child(USER_STRING);
        user.child(USER_ID_STRING)              .setValue(this.id);
        user.child(USER_FIRST_NAME_STRING)      .setValue(this.firstName);
        user.child(USER_USERNAME_STRING)        .setValue(this.username);
        user.child(USER_HASH_PASSWORD_STRING)   .setValue(this.hashedPassword);
        user.child(USER_TYPE_STRING)            .setValue(this.type);
    }

    // TODO
    // Find all Users in db where <param> == <value>
    public static ArrayList<User> dbGetAll(final String param, String value) {

        ArrayList<User> users = new ArrayList<User>();

        // Check param type is an existing user field
        if  (
                !param.equals(USER_ID_STRING) &&
                !param.equals(USER_FIRST_NAME_STRING) &&
                !param.equals(USER_USERNAME_STRING) &&
                !param.equals(USER_HASH_PASSWORD_STRING) &&
                !param.equals(USER_TYPE_STRING)
            ) {
            return users; // TODO Maybe throw an exception?
        }

        return users;
    }


}
