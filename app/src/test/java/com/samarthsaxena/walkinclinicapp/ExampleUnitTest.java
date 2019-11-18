package com.samarthsaxena.walkinclinicapp;

import com.samarthsaxena.walkinclinicapp.backend.facades.Authentication;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.models.User;

import org.junit.Test;

import static com.samarthsaxena.walkinclinicapp.backend.facades.Authentication.getHash;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
   // @Test
   // public void addition_isCorrect() {
        //assertEquals(4, 2 + 2);
    //}

    @Test
    public void checkUsername(){
        final String FAKE_USERNAME = "ax";
        final String FAKE_EMAIL = "x@email.com";
        final String FAKE_PASSWORD = "abcdefgh";
        final String FAKE_TYPE = "patient";
        final User[] x = new User[1];


        Authentication.register(FAKE_EMAIL,FAKE_USERNAME,FAKE_PASSWORD,FAKE_TYPE, new MyCallback() {
            @Override
            public void onCallback(Object value) {
                if (FAKE_TYPE.equals("patient")) {
                    x[0] = new User(FAKE_EMAIL, FAKE_USERNAME, getHash(FAKE_PASSWORD), "patient");
            }

        }

            @Override
            public void exceptionHandler(String message) {

                exceptionHandler(message);
            };
    });

                assertEquals(x[0].getUsername(),FAKE_USERNAME);
}
}