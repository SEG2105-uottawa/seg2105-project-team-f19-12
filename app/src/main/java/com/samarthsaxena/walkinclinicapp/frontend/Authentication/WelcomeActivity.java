package com.samarthsaxena.walkinclinicapp.frontend.Authentication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.samarthsaxena.walkinclinicapp.R;

public class WelcomeActivity extends AppCompatActivity {

    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeText = findViewById(R.id.welcome);

        // Obtain user fields from logging in
        String username = getIntent().getStringExtra("EXTRA_USERNAME");
        String type = getIntent().getStringExtra("EXTRA_USER_TYPE");

        if (username == null) {
            welcomeText.setText("User null");
            return;
        }

        // Display user name and type
        String welcomeMessage = "Welcome " + type + " " + username;
        welcomeText.setText(welcomeMessage);
    }
}
