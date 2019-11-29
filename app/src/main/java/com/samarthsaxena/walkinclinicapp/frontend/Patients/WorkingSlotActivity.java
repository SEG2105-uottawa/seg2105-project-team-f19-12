package com.samarthsaxena.walkinclinicapp.frontend.Patients;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.samarthsaxena.walkinclinicapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WorkingSlotActivity extends AppCompatActivity {

    private TextView welcome;
    private Button viwedate;
    private TextView dateofappoint;
    private TextView viweday;
    private TextView viwetime;
    private ListView serviceList;
    Calendar c;
    DatePickerDialog dpd;
    private TextView day;
    private LinearLayout linearLayout;

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
                        try {
                            dt1 = format1.parse(input_date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        DateFormat format2 = new SimpleDateFormat("EEEE");
                        String finalDay = format2.format(dt1);
                        viweday.setText(finalDay);


                    }
                }, day, month, year);


                dpd.show();


            }
        });


    }

    private void initView() {

        welcome = (TextView) findViewById(R.id.welcome);
        viwedate = (Button) findViewById(R.id.viwedate);
        dateofappoint = (TextView) findViewById(R.id.dateofappoint);
        viweday = (TextView) findViewById(R.id.viweday);
        viwetime = (TextView) findViewById(R.id.viwetime);
        serviceList = (ListView) findViewById(R.id.serviceList);
        day = (TextView) findViewById(R.id.day);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
    }
}