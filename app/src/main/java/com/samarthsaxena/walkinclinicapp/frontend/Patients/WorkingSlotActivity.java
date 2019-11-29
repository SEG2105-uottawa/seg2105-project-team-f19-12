package com.samarthsaxena.walkinclinicapp.frontend.Patients;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.samarthsaxena.walkinclinicapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class WorkingSlotActivity extends AppCompatActivity {

    private TextView welcome;
    private Button viwedate;
    private TextView dateofappoint;
    private TextView viweday;
    private Button viwetime;
    private Button bookappointment;
    private TextView timeslot;
    Calendar c;
    DatePickerDialog dpd;
    TimePickerDialog tpd;
    private TextView day;
    private LinearLayout linearLayout;
    private int minHour = -1;
    private int maxHour = 25;
    int start_time=6;int end_time=14;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customwaitingslotlist);
        initView();
        final String username = getIntent().getStringExtra("EXTRA_USERNAME");
        String welcomeMessage = "Welcome patient " + username;
        welcome.setText(welcomeMessage);


        viwedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);


                dpd = new DatePickerDialog(WorkingSlotActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int years, int months, int dayOfMonth) {

                        dateofappoint.setText(dayOfMonth + "/" + (months + 1) + "/" + years);
                        String input_date = dateofappoint.getText().toString();
                        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                        Date dt1 = null;
                        Date sysDate = c.getTime();
                        try {
                            dt1 = format1.parse(input_date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        DateFormat format2 = new SimpleDateFormat("EEEE");
                        String finalDay = format2.format(dt1);

                        if(dt1.compareTo(sysDate)<0 || years>2019){
//                                Toast.makeText(getApplicationContext(), "Wron", Toast.LENGTH_SHORT).show();
                            dateofappoint.setText("");
                            alertdateDialog();
                        }else {
                            viweday.setText(finalDay);
                        }

                    }
                }, day, month, year);

                dpd.show();


            }
        });
viwetime.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
tpd=new TimePickerDialog(WorkingSlotActivity.this, new TimePickerDialog.OnTimeSetListener() {
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        if(hourOfDay<start_time || hourOfDay>end_time){
            alertDialog();
        }
        else{
            timeslot.setText(hourOfDay+":00");}

        Toast.makeText(getApplicationContext(), "Time must be set in hour intervals only", Toast.LENGTH_SHORT).show();

    }
},0,0,false);

        tpd.show();


    }
});

bookappointment.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(viweday.getText().toString().matches("")||timeslot.getText().toString().matches("")){
            Toast.makeText(getApplicationContext(),"Pls fill all the details",Toast.LENGTH_LONG).show();
        }
        else
        {Toast.makeText(getApplicationContext(),"Appointment booked successfully",Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(WorkingSlotActivity.this, PatientActivity.class);
            myIntent.putExtra("EXTRA_USERNAME", username);
            startActivity(myIntent);
        }
    }
});


    }

    private void initView() {

        welcome = (TextView) findViewById(R.id.welcome);
        viwedate = (Button) findViewById(R.id.viwedate);
        bookappointment = (Button) findViewById(R.id.bookappointment);
        dateofappoint = (TextView) findViewById(R.id.dateofappoint);
        viweday = (TextView) findViewById(R.id.viweday);
        viwetime = (Button) findViewById(R.id.viwetime);
        timeslot = (TextView) findViewById(R.id.timeslot);
        day = (TextView) findViewById(R.id.day);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
    }
    public void setMin(int hour, int minute) {
        minHour = hour;

    }

    public void setMax(int hour, int minute) {
        maxHour = hour;

    }
    private void alertDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Please Select time between the actual time "+start_time+":00 hrs and "+end_time+":00 hrs");
        dialog.setTitle("Error");
        dialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getApplicationContext(),"Ok is clicked",Toast.LENGTH_LONG).show();
                    }
                });
        dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"cancel is clicked",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

    private void alertdateDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Wrong date selected.Please select the correct date");
        dialog.setTitle("Error");
        dialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Toast.makeText(getApplicationContext(),"Ok is clicked",Toast.LENGTH_LONG).show();
                    }
                });
        dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"cancel is clicked",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}
