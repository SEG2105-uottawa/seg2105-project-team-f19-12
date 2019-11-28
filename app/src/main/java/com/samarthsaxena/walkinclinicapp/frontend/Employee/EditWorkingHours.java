package com.samarthsaxena.walkinclinicapp.frontend.Employee;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.samarthsaxena.walkinclinicapp.R;

public class EditWorkingHours extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    private TextView[][] D, T;
    private Button[][] C;
    private Boolean[][] B;

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editworkinghours);

        initVar();

        C[0][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[0][0]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[0][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[0][1]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[0][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[0][2]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[0][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[0][3]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[0][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[0][4]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[0][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[0][5]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[0][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[0][6]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[1][0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[1][0]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[1][1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[1][1]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[1][2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[1][2]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[1][3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[1][3]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[1][4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[1][4]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[1][5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[1][5]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

        C[1][6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                B[1][6]=true;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "Set Start Time");
            }
        });

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        String m;
        if(i<13){
            m="AM";
            if(i==0){
                i=12;
            }
        }else{
            i=i-12;
            m="PM";
        }
        if(i1!=0){
            Toast.makeText(getApplicationContext(), "Time must be set in hour intervals only", Toast.LENGTH_SHORT).show();
        }

        for(int j=0; j<2;j++){
            for(int k=0;k<7;k++){
                if(B[j][k]==true){
                    if(j==0){
                        T[j][k].setText(i+":00 "+m+" ");
                    }else{
                        T[j][k].setText(" "+i+":00 "+m);
                    }
                    B[j][k]=false;
                }
            }
        }
    }

    private void initVar(){
        D=new TextView[2][7];
        T=new TextView[2][7];
        C=new Button[2][7];
        B=new Boolean[2][7];

        //Title
        title=findViewById(R.id.title);

        //Date
        D[0][0]=findViewById(R.id.DT1);
        D[0][1]=findViewById(R.id.DT2);
        D[0][2]=findViewById(R.id.DT3);
        D[0][3]=findViewById(R.id.DT4);
        D[0][4]=findViewById(R.id.DT5);
        D[0][5]=findViewById(R.id.DT6);
        D[0][6]=findViewById(R.id.DT7);

        //Word To
        D[1][0]=findViewById(R.id.W1);
        D[1][1]=findViewById(R.id.W2);
        D[1][2]=findViewById(R.id.W3);
        D[1][3]=findViewById(R.id.W4);
        D[1][4]=findViewById(R.id.W5);
        D[1][5]=findViewById(R.id.W6);
        D[1][6]=findViewById(R.id.W7);

        //Start Time
        T[0][0]=findViewById(R.id.ST1);
        T[0][1]=findViewById(R.id.ST2);
        T[0][2]=findViewById(R.id.ST3);
        T[0][3]=findViewById(R.id.ST4);
        T[0][4]=findViewById(R.id.ST5);
        T[0][5]=findViewById(R.id.ST6);
        T[0][6]=findViewById(R.id.ST7);

        //End Time
        T[1][0]=findViewById(R.id.ET1);
        T[1][1]=findViewById(R.id.ET2);
        T[1][2]=findViewById(R.id.ET3);
        T[1][3]=findViewById(R.id.ET4);
        T[1][4]=findViewById(R.id.ET5);
        T[1][5]=findViewById(R.id.ET6);
        T[1][6]=findViewById(R.id.ET7);

        //Opening Button
        C[0][0]=findViewById(R.id.CB1);
        C[0][1]=findViewById(R.id.CB2);
        C[0][2]=findViewById(R.id.CB3);
        C[0][3]=findViewById(R.id.CB4);
        C[0][4]=findViewById(R.id.CB5);
        C[0][5]=findViewById(R.id.CB6);
        C[0][6]=findViewById(R.id.CB7);

        //Closing Buttons
        C[1][0]=findViewById(R.id.EB1);
        C[1][1]=findViewById(R.id.EB2);
        C[1][2]=findViewById(R.id.EB3);
        C[1][3]=findViewById(R.id.EB4);
        C[1][4]=findViewById(R.id.EB5);
        C[1][5]=findViewById(R.id.EB6);
        C[1][6]=findViewById(R.id.EB7);

        //Button Clicked Array
        for(int i=0;i<7;i++){
            B[0][i]=false;
            B[1][i]=false;
        }

    }

}

