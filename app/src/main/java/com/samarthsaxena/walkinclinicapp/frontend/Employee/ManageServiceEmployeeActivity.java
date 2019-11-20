package com.samarthsaxena.walkinclinicapp.frontend.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.samarthsaxena.walkinclinicapp.R;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.facades.Admin;
import com.samarthsaxena.walkinclinicapp.backend.models.Service;
import com.samarthsaxena.walkinclinicapp.frontend.Admin.CustomListAdapterServices;

import java.text.BreakIterator;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ManageServiceEmployeeActivity extends AppCompatActivity {

    private TextView welcomeText;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemployeeservice);
        welcomeText = findViewById(R.id.welcome);
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

        CustomEmployeeList adapter = new CustomEmployeeList(ManageServiceEmployeeActivity.this, services);
        ListView listView = (ListView) findViewById(R.id.serviceList);
        listView.setAdapter(adapter);


    }

    public void sendMessage(String message) {
        Toast.makeText(ManageServiceEmployeeActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
