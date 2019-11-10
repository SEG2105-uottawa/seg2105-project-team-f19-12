package com.samarthsaxena.walkinclinicapp.frontend.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.samarthsaxena.walkinclinicapp.R;

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
        String services[] = {"Triage", "X-ray", "Check-up"};
        String roles[] = {"role1", "role2", "role3"};

        ArrayList<String> serviceList = new ArrayList<String>(Arrays.asList(services));
        ArrayList<String> roleList = new ArrayList<String>(Arrays.asList(roles));

        CustomListAdapterServices adapter = new CustomListAdapterServices(this, serviceList, roleList);
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


}
