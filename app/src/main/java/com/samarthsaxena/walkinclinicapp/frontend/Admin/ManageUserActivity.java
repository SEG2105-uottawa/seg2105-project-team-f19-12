package com.samarthsaxena.walkinclinicapp.frontend.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.samarthsaxena.walkinclinicapp.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ManageUserActivity extends AppCompatActivity {

    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        homeButton = findViewById(R.id.backButton);

        // Replace fake users with actual users from db
        String usernames[] = {"Alice", "Bob", "Charlie"};
        String emails[] = {"alice@email.com", "bob@email.com", "charlie@email.com"};
        String types[] = {"patient", "employee", "patient"};

        ArrayList<String> usernamesList = new ArrayList<String>(Arrays.asList(usernames));
        ArrayList<String> emailsList = new ArrayList<String>(Arrays.asList(emails));
        ArrayList<String> typesList = new ArrayList<String>(Arrays.asList(types));

        CustomListAdapter adapter = new CustomListAdapter(this, usernamesList, emailsList, typesList);
        ListView listView = (ListView) findViewById(R.id.userlist);
        listView.setAdapter(adapter);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ManageUserActivity.this, AdminActivity.class);
                startActivity(myIntent);
            }
        });
    }


}
