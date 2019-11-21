package com.samarthsaxena.walkinclinicapp.frontend.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.samarthsaxena.walkinclinicapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class ManageServiceActivity extends AppCompatActivity {
    private TextView welcomeText;
    private Button addservice;
    private Button viewservice;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_func);

        welcomeText = findViewById(R.id.welcome);
        desc = findViewById(R.id.profilepage);
        addservice = findViewById(R.id.addservice);
        viewservice = findViewById(R.id.viewservice);
        final String username = getIntent().getStringExtra("EXTRA_USERNAME");
        String welcomeMessage = "Welcome employee "+username;
        welcomeText.setText(welcomeMessage);


        addservice.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(ManageServiceActivity.this, ManageServiceEmployeeActivity.class);
                myIntent.putExtra("EXTRA_USERNAME", username);
                startActivity(myIntent);
            }
        });

        viewservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ManageServiceActivity.this, DeleteServiceEmployeeActivity.class);
                //myIntent.putExtra("EXTRA_USERNAME", username);
                startActivity(myIntent);
            }
        });


    }
}

