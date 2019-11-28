package com.samarthsaxena.walkinclinicapp.frontend.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.samarthsaxena.walkinclinicapp.R;

public class WorkingHoursActivity extends AppCompatActivity {
    private int[][] times;
    private TextView[][] D, T;
    private TextView title;
    private Button Nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workinghours);

        final String username = getIntent().getStringExtra("EXTRA_USERNAME");
        init();

        Nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(WorkingHoursActivity.this, EditWorkingHours.class);
                myIntent.putExtra("EXTRA_USERNAME", username);
                startActivity(myIntent);
            }
        });

    }

    private void init(){
        times=new int[2][7];//temp
        D=new TextView[2][7];
        T=new TextView[2][7];

        //Title
        title=findViewById(R.id.titleWH);

        //Button
        Nav=findViewById(R.id.EB);

        //Date
        D[0][0]=findViewById(R.id.D1);
        D[0][1]=findViewById(R.id.D2);
        D[0][2]=findViewById(R.id.D3);
        D[0][3]=findViewById(R.id.D4);
        D[0][4]=findViewById(R.id.D5);
        D[0][5]=findViewById(R.id.D6);
        D[0][6]=findViewById(R.id.D7);

        //Word To
        D[1][0]=findViewById(R.id.X1);
        D[1][1]=findViewById(R.id.X2);
        D[1][2]=findViewById(R.id.X3);
        D[1][3]=findViewById(R.id.X4);
        D[1][4]=findViewById(R.id.X5);
        D[1][5]=findViewById(R.id.X6);
        D[1][6]=findViewById(R.id.X7);

        //Start Time
        T[0][0]=findViewById(R.id.S1);
        T[0][1]=findViewById(R.id.S2);
        T[0][2]=findViewById(R.id.S3);
        T[0][3]=findViewById(R.id.S4);
        T[0][4]=findViewById(R.id.S5);
        T[0][5]=findViewById(R.id.S6);
        T[0][6]=findViewById(R.id.S7);

        //End Time
        T[1][0]=findViewById(R.id.E1);
        T[1][1]=findViewById(R.id.E2);
        T[1][2]=findViewById(R.id.E3);
        T[1][3]=findViewById(R.id.E4);
        T[1][4]=findViewById(R.id.E5);
        T[1][5]=findViewById(R.id.E6);
        T[1][6]=findViewById(R.id.E7);

        //Temp
        for(int i=0;i<7;i++){
            times[0][i]=6;
            times[1][i]=24;
        }
        setHours();

    }

    private void setHours(){
        for(int i=0;i<7;i++){
            if(times[0][i]<13){
                T[0][i].setText(times[0][i]+":00 AM ");
            }else{
                T[0][i].setText(times[0][i]-12+":00 PM ");
            }

            if(times[1][i]<13){
                T[1][i].setText(" "+times[1][i]+":00 AM");
            }else{
                T[1][i].setText(" "+(times[1][i]-12)+":00 PM");
            }

        }
    }

}
