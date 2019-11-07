package com.samarthsaxena.walkinclinicapp.frontend.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.samarthsaxena.walkinclinicapp.R;

public class AdminActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button manageUsersButton;
    private Button manageServicesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        welcomeText = findViewById(R.id.welcome);
        manageServicesButton = findViewById(R.id.serviceButton);
        manageUsersButton = findViewById(R.id.userButton);

        String welcomeMessage = "Welcome admin";
        welcomeText.setText(welcomeMessage);

        manageUsersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Go to User manager layout
                Intent myIntent = new Intent(AdminActivity.this, ManageUserActivity.class);
                startActivity(myIntent);
            }
        });

        manageServicesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Go to User manager layout
                Intent myIntent = new Intent(AdminActivity.this, ManageServiceActivity.class);
                startActivity(myIntent);
            }
        });


    }
}
