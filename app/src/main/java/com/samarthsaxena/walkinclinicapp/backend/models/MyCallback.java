package com.samarthsaxena.walkinclinicapp.backend.models;

import java.util.ArrayList;

/*
*  Interface needed to call functions after asynchronous methods
*  are called, in particular, after firebase is done retrieving
*  data.
*/
public interface MyCallback {
    void onCallback(Object value);
}
