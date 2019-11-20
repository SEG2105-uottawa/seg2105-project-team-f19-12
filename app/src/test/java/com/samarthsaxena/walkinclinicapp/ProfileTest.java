package com.samarthsaxena.walkinclinicapp;

import com.samarthsaxena.walkinclinicapp.backend.models.Profile;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProfileTest {


    Profile FAKE_PROFILE;
    String FAKE_USER="fakeAdmin";
    String FAKE_ADDRESS="73AddressLane";
    int FAKE_PHONENUMBER=5555555555;
    String FAKE_CLINIC="tClinic";
    String FAKE_INSURANCETYPE="Dental";
    String FAKE_PAYMENTMETHOD="Visa";

    @Test
    void testBlankInitialization(){
        FAKE_PROFILE=new Profile();
    }

    @Test
    void testInitialization(){
        FAKE_PROFILE = new Profile(FAKE_USER, FAKE_ADDRESS, FAKE_PHONENUMBER, FAKE_CLINIC, FAKE_INSURANCETYPE, FAKE_PAYMENTMETHOD);
    }
}
