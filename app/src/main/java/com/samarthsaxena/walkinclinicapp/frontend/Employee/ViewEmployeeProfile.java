package com.samarthsaxena.walkinclinicapp.frontend.Employee;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.samarthsaxena.walkinclinicapp.R;

import com.samarthsaxena.walkinclinicapp.backend.facades.Employee;
import com.samarthsaxena.walkinclinicapp.backend.models.Profile;
import com.samarthsaxena.walkinclinicapp.backend.models.Service;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewEmployeeProfile extends AppCompatActivity {

    private TextView welcomeText;
    private TextView fullnameText;
    private TextView phoneText;
    private TextView addressText;
    private TextView insuranceText;
    private TextView payText;
    private ListView employeehours;

    //private ListView employeeprofile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofile);

        welcomeText = findViewById(R.id.welcome);
        fullnameText=findViewById(R.id.fullnametext);
        phoneText=findViewById(R.id.phonetext);
        addressText=findViewById(R.id.addressText);
        insuranceText=findViewById(R.id.insurancetext);
        payText=findViewById(R.id.paytext);
        employeehours=findViewById(R.id.workinghourList);




        final String username = getIntent().getStringExtra("EXTRA_USERNAME");

        // Display user name
        String welcomeMessage = "Welcome employee "+username;

        welcomeText.setText(welcomeMessage);
        Profile employeeprofile = Employee.viewProfile(username);
        fullnameText.setText(employeeprofile.getClinic());
        phoneText.setText(employeeprofile.getPhoneNumber());
        payText.setText(employeeprofile.getPaymentMethod());
        insuranceText.setText(employeeprofile.getInsuranceType());
        addressText.setText(employeeprofile.getAddress());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, employeeprofile.getWorkingTime());
        employeehours.setAdapter(adapter);

    }

}
