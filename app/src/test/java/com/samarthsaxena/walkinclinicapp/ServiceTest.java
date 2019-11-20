package com.samarthsaxena.walkinclinicapp;

import com.samarthsaxena.walkinclinicapp.backend.models.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceTest {
    Service FAKE_SERVICE;
    String FAKE_SERVICEOFFERED = "Blood";
    String FAKE_ROLE = "Nurse";

    @Test
    void blankServiceInitializationTest(){
        FAKE_SERVICE = new Service();
    }

    @Test
    void ServiceInitializationTest(){
        FAKE_SERVICE = new Service(FAKE_SERVICEOFFERED, FAKE_ROLE);
        assertEquals(FAKE_SERVICEOFFERED, FAKE_SERVICE.getServiceOffered());
        assertEquals(FAKE_ROLE, FAKE_SERVICE.getRole());
    }

}
