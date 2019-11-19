package com.samarthsaxena.walkinclinicapp.frontend.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.samarthsaxena.walkinclinicapp.R;
import com.samarthsaxena.walkinclinicapp.frontend.WelcomeActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private RadioButton UHIPOption;
    private RadioButton OHIPOption;
    private RadioButton NOOption;
    private RadioButton CreditCard;
    private RadioButton DebitCard;
    private RadioButton Cash;
    private EditText fullnameText;
    private EditText phoneText;
    private EditText addressText;
    private Button Submit;
    private Button Back;
    private TextView welcomeText;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        UHIPOption = findViewById(R.id.insuranceuhip);
        OHIPOption = findViewById(R.id.insuranceohip);
        NOOption = findViewById(R.id.insuranceno);
        CreditCard = findViewById(R.id.paycreditcard);
        DebitCard = findViewById(R.id.paydebitcard);
        Cash = findViewById(R.id.paycash);
        fullnameText = findViewById(R.id.fullnametext);
        addressText = findViewById(R.id.addressText);
        phoneText = findViewById(R.id.phonetext);
        Submit = findViewById(R.id.submit);
        Back = findViewById(R.id.back);
        welcomeText = findViewById(R.id.welcome);

        // Obtain user fields from logging in
        String username = getIntent().getStringExtra("EXTRA_USERNAME");

        // Display user name
        String welcomeMessage = "Welcome employee "+username;
        welcomeText.setText(welcomeMessage);



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obtain fields from UI
                final String fullname = fullnameText.getText().toString();
                final String address = addressText.getText().toString();
                final String phone = phoneText.getText().toString();
                String insurancesel = "";
                String paysel = "";

                // Check fields aren't empty
                if (fullname.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                    sendMessage("Empty fields aren't allowed!");
                    return;
                }

                // Determine insurance type
                if (UHIPOption.isChecked()) {
                    insurancesel = "UHIP";
                }
                if (OHIPOption.isChecked())
                {insurancesel="OHIP";
                }
                if (NOOption.isChecked())
                {
                    insurancesel="No";
                }
                // Determine pay type
                if (CreditCard.isChecked()) {
                    paysel = "CreditCard";
                }
                if (DebitCard.isChecked())
                {paysel="DebitCard";
                }
                if (Cash.isChecked())
                {
                    paysel="Cash";
                }

                //TODO: Register employee profile


            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfileActivity.this, EmployeeActivity.class);
                startActivity(myIntent);
            }
        });

    }

    public void sendMessage(String message) {
        Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_LONG).show();
    }
}



