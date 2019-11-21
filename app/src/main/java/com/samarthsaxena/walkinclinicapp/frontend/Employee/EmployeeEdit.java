package com.samarthsaxena.walkinclinicapp.frontend.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.samarthsaxena.walkinclinicapp.R;
import com.samarthsaxena.walkinclinicapp.backend.facades.Employee;
import com.samarthsaxena.walkinclinicapp.backend.models.Profile;

import android.widget.TimePicker;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EmployeeEdit extends AppCompatActivity {
    private RadioButton UHIPOption;
    private RadioButton OHIPOption;
    private RadioButton NOOption;
    private RadioButton CreditCard;
    private RadioButton DebitCard;
    private RadioButton Cash;
    private TextView fullnameText;
    private TextView phoneText;
    private TextView addressText;
    private Button Save;
    private Button Back;
    private TextView welcomeText;
    private TimePicker timestartPicker;
    private TimePicker timeendPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeeedit);


        UHIPOption = findViewById(R.id.insuranceuhip);
        OHIPOption = findViewById(R.id.insuranceohip);
        NOOption = findViewById(R.id.insuranceno);
        CreditCard = findViewById(R.id.paycreditcard);
        DebitCard = findViewById(R.id.paydebitcard);
        Cash = findViewById(R.id.paycash);
        fullnameText = findViewById(R.id.fullnametext);
        addressText = findViewById(R.id.addressText);
        phoneText = findViewById(R.id.phonetext);
        Save = findViewById(R.id.save);
        Back = findViewById(R.id.back);
        welcomeText = findViewById(R.id.welcome);
        timestartPicker = (TimePicker) findViewById(R.id.timePicker2);
        timeendPicker = (TimePicker) findViewById(R.id.timePicker1);
        // Obtain user fields from logging in
        final String username = getIntent().getStringExtra("EXTRA_USERNAME");
        final String address = getIntent().getStringExtra("EXTRA_address");
        final String phone = getIntent().getStringExtra("EXTRA_phone");
        final String fullname = getIntent().getStringExtra("EXTRA_fullname");
        final String pay = getIntent().getStringExtra("EXTRA_pay");
        final String insurance = getIntent().getStringExtra("EXTRA_insurance");
        final String time = getIntent().getStringExtra("EXTRA_time");

        // Display user info
        final String welcomeMessage = "Welcome employee "+username;
        welcomeText.setText(welcomeMessage);
        fullnameText.setText(fullname);
        addressText.setText(address);
        phoneText.setText(phone);
        final String checkinsurance = insurance;
        final String checkpay = pay;
        final String times=time;
        final ArrayList<String> time_slot = new ArrayList<>();


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obtain fields from UI
//                final String fullname = fullnameText.getText().toString();;
//                final String address = addressText.getText().toString();
//                final int phone = Integer.parseInt(phoneText.getText().toString());
//

                int hourstart = timestartPicker.getCurrentHour();
                int minstart = timestartPicker.getCurrentMinute();
                int hourend= timeendPicker.getCurrentHour();
                int minend = timeendPicker.getCurrentMinute();
                String starttime=showstartTime(hourstart,minstart);
                String endtime=showendTime(hourend,minend);
                // Check fields aren't empty

                // Determine insurance type
                if (checkinsurance==("UHIP"))
                {
                    UHIPOption.setChecked(true);
                    OHIPOption.setChecked(false);
                    NOOption.setChecked(false);
                }
                if (checkinsurance==("OHIP"))
                {
                    OHIPOption.setChecked(true);
                    NOOption.setChecked(false);
                    UHIPOption.setChecked(false);
                }
                if (checkinsurance==("No"))
                {
                    NOOption.setChecked(true);
                    UHIPOption.setChecked(false);
                    OHIPOption.setChecked(false);
                }

                if (checkpay==("CreditCard"))
                {
                    CreditCard.setChecked(true);
                    DebitCard.setChecked(false);
                    Cash.setChecked(false);
                }
                if (checkpay==("DebitCard"))
                {
                    DebitCard.setChecked(true);
                    CreditCard.setChecked(false);
                    Cash.setChecked(false);
                }
                if (checkpay==("Cash"))
                {
                    Cash.setChecked(true);
                    CreditCard.setChecked(false);
                    DebitCard.setChecked(false);
                }

                String monday =("Monday: " + starttime +" to "+ endtime);
                String tuesday =("Tuesday: " + starttime +" to "+ endtime);
                String wednesday =("Wednesday: " + starttime +" to "+ endtime);
                String thursday =("Thursday: " + starttime +" to "+ endtime);
                String friday =("Friday: " + starttime +" to "+ endtime);
                String saturday =("Saturday: " + starttime +" to "+ endtime);
                String sunday =("Sunday: " + starttime +" to "+ endtime);
                time_slot.add(monday);
                time_slot.add(tuesday);
                time_slot.add(wednesday);
                time_slot.add(thursday);
                time_slot.add(friday);
                time_slot.add(saturday);
                time_slot.add(sunday);

                String a="User profile edited";
               //TODO:Edit method
                Employee.editProfile(username, Profile.PROFILE_TIME_STRING, time_slot);
                Toast.makeText(EmployeeEdit.this, a, Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(EmployeeEdit.this, ManageProfileActivity.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_USERNAME",username);
                extras.putString("EXTRA_address", address);
                extras.putString("EXTRA_phone", phone);
                extras.putString("EXTRA_fullname", fullname);
                extras.putString("EXTRA_insurance", insurance);
                extras.putString("EXTRA_pay", pay);
                extras.putString("EXTRA_time", time);
                myIntent.putExtras(extras);
                startActivity(myIntent);

            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(EmployeeEdit.this, EmployeeActivity.class);
                myIntent.putExtra("EXTRA_USERNAME", username);
                startActivity(myIntent);
            }
        });

    }
    String starttime="";
    public String showstartTime(int hour, int min) {
        String format="";
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        starttime=(hour)+" : "+(min)+" "+format;
        return starttime;


    }

    String endtime="";
    public String showendTime(int hour, int min) {
        String format="";
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        endtime=(hour)+" : "+(min)+" "+format;
        return endtime;


    }


    public void sendMessage(String message) {
        Toast.makeText(EmployeeEdit.this, message, Toast.LENGTH_LONG).show();
    }


}



