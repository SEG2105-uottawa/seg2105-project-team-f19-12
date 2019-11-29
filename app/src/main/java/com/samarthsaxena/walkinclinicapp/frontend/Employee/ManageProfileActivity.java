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

public class ManageProfileActivity extends AppCompatActivity {
    private TextView welcomeText;
    private Button addbutton;
    private Button editbutton;
    private Button viewbutton;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_func);

        welcomeText = findViewById(R.id.welcome);
        desc = findViewById(R.id.profilepage);
        addbutton = findViewById(R.id.addprofile);
        //editbutton = findViewById(R.id.editprofile);
        viewbutton = findViewById(R.id.viewprofile);
        final String username = getIntent().getStringExtra("EXTRA_USERNAME");
        String welcomeMessage = "Welcome employee "+username;
        welcomeText.setText(welcomeMessage);


        addbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Profile.dbGetAll(Profile.PROFILE_USER_STRING, username, new MyCallback() {
                    @Override
                    public void onCallback(Object value) {
                        ArrayList<Profile> profiles = (ArrayList<Profile>) value;
                        if (!profiles.isEmpty()) {
                            sendMessage("This employee already has a profile!");
                            return;
                        } else {
                            Intent myIntent = new Intent(ManageProfileActivity.this, ProfileActivity.class);
                            myIntent.putExtra("EXTRA_USERNAME", username);
                            startActivity(myIntent);
                        }
                    }

                    @Override
                    public void exceptionHandler(String message) {

                    }
                });
            }
        });

        /*
        editbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(ManageProfileActivity.this, EmployeeEdit.class);
                myIntent.putExtra("EXTRA_USERNAME", username);
                startActivity(myIntent);
            }
        });

         */
        viewbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Profile.dbGetAll(Profile.PROFILE_USER_STRING, username, new MyCallback() {
                    @Override
                    public void onCallback(Object value) {
                        ArrayList<Profile> profiles = (ArrayList<Profile>) value;
                        if (profiles.isEmpty()) {
                            sendMessage("This employee has no profile yet!");
                            return;
                        } else {
                            Intent myIntent = new Intent(ManageProfileActivity.this, ViewEmployeeProfile.class);
                            myIntent.putExtra("EXTRA_USERNAME", username);
                            startActivity(myIntent);
                        }
                    }

                    @Override
                    public void exceptionHandler(String message) {

                    }
                });
            }
        });

    }

    public void sendMessage(String message) {
        Toast.makeText(ManageProfileActivity.this, message, Toast.LENGTH_LONG).show();
    }
}


