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
import com.samarthsaxena.walkinclinicapp.backend.models.User;

import java.util.ArrayList;

public class ManageUserActivity extends AppCompatActivity {

    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        homeButton = findViewById(R.id.backButton);

        // Replace fake users with actual users from db

        ArrayList<User> users = User.dbGetAll("all", null, new MyCallback() {
            @Override
            public void onCallback(Object value) {

            }

            @Override
            public void exceptionHandler(String message) {
                sendMessage("Error: Could not load users from database");
            }
        });

        CustomListAdapterUser adapter = new CustomListAdapterUser(ManageUserActivity.this, users);
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

    public void sendMessage(String message) {
        Toast.makeText(ManageUserActivity.this, message, Toast.LENGTH_LONG).show();
    }

}
