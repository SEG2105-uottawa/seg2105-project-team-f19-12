package com.samarthsaxena.walkinclinicapp.backend.facades;

import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
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
        User.dbGetAll(User.USER_USERNAME_STRING, username, new MyCallback() {
            @Override
            public void onCallback(Object obj) {
                ArrayList<User> value = (ArrayList<User>) obj;
                if (value.isEmpty()) {
                    exceptionHandler("Username does not exist!");
                    return;
                }
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

    public static void register(final String email,
                                final String username,
                                final String password,
                                final String type,
                                final MyCallback cb) {

        User.dbGetAll(User.USER_USERNAME_STRING, username, new MyCallback() {
            @Override
            public void onCallback(Object value) {
                ArrayList<User> users = (ArrayList<User>) value;
                if (!users.isEmpty()) {
                    exceptionHandler("Username already taken!");
                    return;
                }
                User out = new User(email, username, getHash(password), type);
                out.dbStore(cb);
            }

            @Override
            public void exceptionHandler(String message) {

                cb.exceptionHandler(message);
            }
        });
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



