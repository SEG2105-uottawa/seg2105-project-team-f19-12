package com.samarthsaxena.walkinclinicapp.frontend.Employee;


import android.os.Bundle;


import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.samarthsaxena.walkinclinicapp.R;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.facades.Admin;
import com.samarthsaxena.walkinclinicapp.backend.models.Service;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class Manageemployeeservice extends AppCompatActivity {

    private TextView welcomeText;
    private EditText text;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemployeeservice);
        welcomeText = findViewById(R.id.welcome);
        text=findViewById(R.id.fullnametext);
        final String username = getIntent().getStringExtra("EXTRA_USERNAME");

        // Display user name
        String welcomeMessage = "Welcome employee "+username;

        welcomeText.setText(welcomeMessage);



        // Replace fake users with actual users from db
        ArrayList<Service> services = Admin.getAllServices(new MyCallback() {
            @Override
            public void onCallback(Object value) {

            }

            @Override
            public void exceptionHandler(String message) {
                sendMessage(message);
            }
        });

        CustomEmployeeList adapter = new CustomEmployeeList(Manageemployeeservice.this, services);
        ListView listView = (ListView) findViewById(R.id.serviceList);
        listView.setAdapter(adapter);


    }

    public void sendMessage(String message) {
        Toast.makeText(Manageemployeeservice.this, message, Toast.LENGTH_LONG).show();
    }
}
