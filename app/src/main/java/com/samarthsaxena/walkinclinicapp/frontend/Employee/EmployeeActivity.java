package com.samarthsaxena.walkinclinicapp.frontend.Employee;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.samarthsaxena.walkinclinicapp.R;


import androidx.appcompat.app.AppCompatActivity;

public class EmployeeActivity extends AppCompatActivity {
    private TextView welcomeText;
    private Button profileButton;
    private Button manageServicesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        welcomeText = findViewById(R.id.welcome);
        manageServicesButton = findViewById(R.id.serviceButton);
        profileButton = findViewById(R.id.profileButton);
        final String username = getIntent().getStringExtra("EXTRA_USERNAME");
        String welcomeMessage = "Welcome employee "+username;
        welcomeText.setText(welcomeMessage);


        profileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(EmployeeActivity.this, ProfileActivity.class);
                myIntent.putExtra("EXTRA_USERNAME", username);
                startActivity(myIntent);
            }
        });


    }
}


