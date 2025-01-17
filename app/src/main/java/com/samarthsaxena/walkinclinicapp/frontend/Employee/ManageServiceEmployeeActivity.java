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

public class ManageServiceEmployeeActivity extends AppCompatActivity {

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

        final CustomEmployeeList adapter = new CustomEmployeeList(ManageServiceEmployeeActivity.this, services, username);
        final ListView listView = (ListView) findViewById(R.id.serviceList);
        listView.postDelayed(new Runnable() {
            public void run() {
                listView.setAdapter(adapter);
            }
        }, 400);


    }

    public void sendMessage(String message) {
        Toast.makeText(ManageServiceEmployeeActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
