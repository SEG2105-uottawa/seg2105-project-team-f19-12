package com.samarthsaxena.walkinclinicapp.backend;

import com.samarthsaxena.walkinclinicapp.backend.models.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Authentication {

    private Authentication() {
        // No ctor
    }

    // Login method with callback
    public static void login(final String username, final String password, final MyCallback cb) {

        // Check username is registered
        User.dbGetAll("username", username, new MyCallback() {
            @Override
            public void onCallback(Object obj) {
                ArrayList<User> value = (ArrayList<User>) obj;
                User user = value.get(0);
                if (!username.equals(user.getUsername())) {
                    exceptionHandler("Username does not exist!");
                    return;
                }
                if (!getHash(password).equals(user.getHashedPassword())) {
                    exceptionHandler("Password is incorrect!");
                    return;
                }
                cb.onCallback(user);
            }

            @Override
            public void exceptionHandler(String message) {
                cb.exceptionHandler(message);
            }
        });
    }

    public static User register(String email, String username, String password, String type, MyCallback cb) {

        User out;
        if (type.equals("patient")) {
            out = new Patient(email, username, getHash(password));
        } else {
            out = new Employee(email, username, getHash(password));
        }
        out.dbStore(cb);
        return out;
    }

    public static String getHash(String password) {
        try {
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            }
            digest.reset();
            return convert(digest.digest(password.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }

    private static String convert(byte[] data) {
        StringBuilder x = new StringBuilder(data.length * 2);
        for (byte b : data)
            x.append(String.format("%02x", b & 0xFF));
        return x.toString();
    }

}



