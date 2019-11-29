package com.samarthsaxena.walkinclinicapp.frontend.Patients;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.samarthsaxena.walkinclinicapp.R;

import java.util.ArrayList;

public class AppointmentSearch extends AppCompatActivity {
    EditText search;
    RadioButton address, wh, service;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        search=findViewById(R.id.searchText);
        address=findViewById(R.id.RB1);
        wh=findViewById(R.id.RB2);
        service=findViewById(R.id.RB3);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentsearch);
        list=findViewById(R.id.LV);


        SearchAdapter adapter = new SearchAdapter();
        list.setAdapter(adapter);

    }

}
