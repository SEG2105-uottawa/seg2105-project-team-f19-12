package com.samarthsaxena.walkinclinicapp.frontend.Patients;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.samarthsaxena.walkinclinicapp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RateActivity extends AppCompatActivity {
    private LinearLayout linearLayout;
    private TextView welcome;
    private TextView name;
    private TextView employeename;
    private TextView rate;
    private RatingBar rating;
    private TextView description;
    private TextView ename;
    private Button submitrating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        initView();
        final String username = getIntent().getStringExtra("EXTRA_USERNAME");
        String welcomeMessage = "Welcome patient " + username;
        welcome.setText(welcomeMessage);


        submitrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(description.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),"Pls rate the employee",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent myIntent = new Intent(RateActivity.this, PatientActivity.class);
                    myIntent.putExtra("EXTRA_USERNAME", username);
                    startActivity(myIntent);
                }
            }
        });
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        welcome = (TextView) findViewById(R.id.welcome);
        name = (TextView) findViewById(R.id.name);
        employeename = (TextView) findViewById(R.id.employeename);
        rate = (TextView) findViewById(R.id.rate);
        rating = (RatingBar) findViewById(R.id.rating);
        description = (TextView) findViewById(R.id.description);
        ename = (TextView) findViewById(R.id.ename);
        submitrating = (Button) findViewById(R.id.submitrating);
    }
}
