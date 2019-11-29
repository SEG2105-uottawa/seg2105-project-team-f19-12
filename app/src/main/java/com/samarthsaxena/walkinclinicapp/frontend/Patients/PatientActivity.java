package com.samarthsaxena.walkinclinicapp.frontend.Patients;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.samarthsaxena.walkinclinicapp.R;


import androidx.appcompat.app.AppCompatActivity;

public class PatientActivity extends AppCompatActivity {
    private TextView welcomeText;
    private Button bookButton;
    private Button rateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        welcomeText = findViewById(R.id.welcome);
        bookButton = findViewById(R.id.bookButton);
        rateButton = findViewById(R.id.rateButton);

        final String username = getIntent().getStringExtra("EXTRA_USERNAME");
        String welcomeMessage = "Welcome patient "+username;
        welcomeText.setText(welcomeMessage);

        bookButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(PatientActivity.this, AppointmentSearch.class);
                myIntent.putExtra("EXTRA_USERNAME", username);
                startActivity(myIntent);
            }
        });

        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PatientActivity.this, RateActivity.class);
                myIntent.putExtra("EXTRA_USERNAME", username);
                startActivity(myIntent);
            }
        });


    }
}


