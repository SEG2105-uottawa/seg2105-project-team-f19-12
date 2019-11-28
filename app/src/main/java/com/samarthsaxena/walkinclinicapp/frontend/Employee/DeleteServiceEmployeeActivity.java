package com.samarthsaxena.walkinclinicapp.frontend.Employee;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.samarthsaxena.walkinclinicapp.R;
import com.samarthsaxena.walkinclinicapp.backend.facades.Employee;
import com.samarthsaxena.walkinclinicapp.backend.models.Service;

import java.util.ArrayList;

public class DeleteServiceEmployeeActivity extends AppCompatActivity {
    private TextView welcomeText;
    private EditText text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_deleteemployeeservice);
        welcomeText = findViewById(R.id.welcome);
        text = findViewById(R.id.fullnametext);
        final String username = getIntent().getStringExtra("EXTRA_USERNAME");
        ArrayList<Service> profileservice = Employee.viewServicesOfUser(username);

        final CustomEmployeeDeleteList adapter = new CustomEmployeeDeleteList(DeleteServiceEmployeeActivity.this, profileservice, username);
        final ListView listView = (ListView) findViewById(R.id.serviceList);
        listView.postDelayed(new Runnable() {
            public void run() {
                listView.setAdapter(adapter);
            }
        }, 400);


    }
}
