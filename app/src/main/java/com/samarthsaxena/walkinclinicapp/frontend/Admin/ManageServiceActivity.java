package com.samarthsaxena.walkinclinicapp.frontend.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.samarthsaxena.walkinclinicapp.R;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.models.Service;

import java.util.ArrayList;
import java.util.Arrays;

public class ManageServiceActivity extends AppCompatActivity {

    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);

        homeButton = findViewById(R.id.backButton);

        // Replace fake users with actual users from db
        ArrayList<Service> services = Service.dbGetAll("all", null, new MyCallback() {
            @Override
            public void onCallback(Object value) {

            }

            @Override
            public void exceptionHandler(String message) {
                sendMessage("Error: Could not load users from database");
            }
        });

        CustomListAdapterServices adapter = new CustomListAdapterServices(ManageServiceActivity.this, services);
        ListView listView = (ListView) findViewById(R.id.serviceList);
        listView.setAdapter(adapter);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ManageServiceActivity.this, AdminActivity.class);
                startActivity(myIntent);
            }
        });
    }

    public void sendMessage(String message) {
        Toast.makeText(ManageServiceActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
