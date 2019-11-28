package com.samarthsaxena.walkinclinicapp.frontend.Employee;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.samarthsaxena.walkinclinicapp.R;

public class AddWorkingHours extends AppCompatActivity {
    private TextView monday;
    private TextView tuesday;
    private TextView wednesday;
    private TextView thursday;
    private TextView friday;
    private TextView saturday;
    private TextView sunday;
    private Button addbutton1;
    private Button addbutton2;
    private Button addbutton3;
    private Button addbutton4;
    private Button addbutton5;
    private Button addbutton6;
    private Button addbutton7;
    private int[][] Hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addworkinghours);

        monday=findViewById(R.id.DateTextMon);
        tuesday=findViewById(R.id.DateTextTues);
        wednesday=findViewById(R.id.DateTextWed);
        thursday=findViewById(R.id.DateTextThurs);
        friday=findViewById(R.id.DateTextFriday);
        saturday=findViewById(R.id.DateTextSat);
        sunday=findViewById(R.id.DateTextSun);

        addbutton1=findViewById(R.id.AddButton1);
        addbutton2=findViewById(R.id.AddButton2);
        addbutton3=findViewById(R.id.AddButton3);
        addbutton4=findViewById(R.id.AddButton4);
        addbutton5=findViewById(R.id.AddButton5);
        addbutton6=findViewById(R.id.AddButton6);
        addbutton7=findViewById(R.id.AddButton7);

        addbutton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        addbutton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        addbutton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        addbutton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        addbutton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        addbutton6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        addbutton7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

        setHours();
        initRecyclerView();
    }

    private void setHours(){
        Hours=new int[2][7];

        for(int i=0;i<2;i++){
            for(int j=0;j<7;j++){
                Hours[i][j]=3;
            }
        }

    }

    private void initRecyclerView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView recyclerViewM = findViewById(R.id.recyclerViewMon);
        RecyclerView recyclerViewTu = findViewById(R.id.recyclerViewTues);
        RecyclerView recyclerViewW = findViewById(R.id.recyclerViewWed);
        RecyclerView recyclerViewTh = findViewById(R.id.recyclerViewThurs);
        RecyclerView recyclerViewF = findViewById(R.id.recyclerViewFri);
        RecyclerView recyclerViewSa = findViewById(R.id.recyclerViewSat);
        RecyclerView recyclerViewSu = findViewById(R.id.recyclerViewSun);

        recyclerViewM.setLayoutManager(layoutManager);
        recyclerViewTu.setLayoutManager(layoutManager);
        recyclerViewW.setLayoutManager(layoutManager);
        recyclerViewTh.setLayoutManager(layoutManager);
        recyclerViewF.setLayoutManager(layoutManager);
        recyclerViewSa.setLayoutManager(layoutManager);
        recyclerViewSu.setLayoutManager(layoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, Hours);

        recyclerViewM.setAdapter(adapter);
        recyclerViewTu.setAdapter(adapter);
        recyclerViewW.setAdapter(adapter);
        recyclerViewTh.setAdapter(adapter);
        recyclerViewF.setAdapter(adapter);
        recyclerViewSa.setAdapter(adapter);
        recyclerViewSu.setAdapter(adapter);
    }

}

