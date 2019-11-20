package com.samarthsaxena.walkinclinicapp.frontend.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.samarthsaxena.walkinclinicapp.R;

public class MainActivity extends AppCompatActivity {

    Button signUp;
    Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp = findViewById(R.id.signup);
        logIn = findViewById(R.id.login);

        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        SignupActivity.class);
                startActivity(myIntent);
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        LoginActivity.class);
                startActivity(myIntent);
            }
        });
    }
}