package com.samarthsaxena.walkinclinicapp.frontend;

import android.os.Bundle;
import android.util.Log;
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

        String username = getIntent().getStringExtra("EXTRA_USERNAME");

        Log.i("OOOOOOOOOOOOOOOOO", username);
        if (username != null) {
            String welcomeMessage = "Welcome ".concat(username);
            welcomeText.setText(welcomeMessage);
        } else {
            welcomeText.setText("User null");
        }
    }
}
