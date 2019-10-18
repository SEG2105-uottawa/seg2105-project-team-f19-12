package com.samarthsaxena.walkinclinicapp.backend;

import com.samarthsaxena.walkinclinicapp.backend.models.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authentication {

    public static User login() {
        // TODO
        return null;
    }

    public static User register() {
        // TODO
        return null;
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



