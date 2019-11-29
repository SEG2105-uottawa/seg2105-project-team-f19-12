package com.samarthsaxena.walkinclinicapp.frontend.Employee;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.samarthsaxena.walkinclinicapp.R;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.models.Profile;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity {
    private TextView welcomeText;
    private Button profileButton;
    private Button manageServicesButton;
    private  Button workingHoursButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        welcomeText = findViewById(R.id.welcome);
        manageServicesButton = findViewById(R.id.serviceButton);
        profileButton = findViewById(R.id.profileButton);
        workingHoursButton= findViewById(R.id.WorkingHoursButton);
        final String username = getIntent().getStringExtra("EXTRA_USERNAME");
        String welcomeMessage = "Welcome employee "+username;
        welcomeText.setText(welcomeMessage);


        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(EmployeeActivity.this, ManageProfileActivity.class);
                myIntent.putExtra("EXTRA_USERNAME", username);
                startActivity(myIntent);
            }
        });
        manageServicesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(EmployeeActivity.this, ManageServiceActivity.class);
                myIntent.putExtra("EXTRA_USERNAME", username);
                startActivity(myIntent);
            }
        });

        workingHoursButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Profile.dbGetAll(Profile.PROFILE_USER_STRING, username, new MyCallback() {
                    @Override
                    public void onCallback(Object value) {
                        ArrayList<Profile> profiles = (ArrayList<Profile>) value;
                        if (profiles.isEmpty()) {
                            sendMessage("Error: Please create a clinic profile first!");
                            return;
                        }
                        Intent myIntent = new Intent(EmployeeActivity.this, WorkingHoursActivity.class);
                        myIntent.putExtra("EXTRA_USERNAME", username);
                        startActivity(myIntent);
                    }

                    @Override
                    public void exceptionHandler(String message) {

                    }
                });
            }
        });

    }

    public void sendMessage(String message) {
        Toast.makeText(EmployeeActivity.this, message, Toast.LENGTH_LONG).show();
    }
}


