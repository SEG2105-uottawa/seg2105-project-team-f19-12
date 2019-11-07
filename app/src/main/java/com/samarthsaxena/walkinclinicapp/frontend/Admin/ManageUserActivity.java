package com.samarthsaxena.walkinclinicapp.frontend.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.samarthsaxena.walkinclinicapp.R;

public class ManageUserActivity extends AppCompatActivity {

    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        homeButton = findViewById(R.id.backButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ManageUserActivity.this, AdminActivity.class);
                startActivity(myIntent);
            }
        });
    }


}
