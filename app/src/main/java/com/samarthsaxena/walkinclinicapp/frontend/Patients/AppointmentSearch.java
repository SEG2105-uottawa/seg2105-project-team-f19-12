package com.samarthsaxena.walkinclinicapp.frontend.Patients;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.samarthsaxena.walkinclinicapp.R;

import java.util.ArrayList;

public class AppointmentSearch extends AppCompatActivity {
    private EditText searchText;
    private RadioButton address, service, wh;
    private Spinner myDateSpinner;
    private Button searchBtn;
    private ListView list;
    private int dayOfWeek;
    private String searchTextString;
    private SearchFilter SF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentsearch);

        searchText=findViewById(R.id.searchText);
        address=findViewById(R.id.RB1);
        wh=findViewById(R.id.RB2);
        service=findViewById(R.id.RB3);
        myDateSpinner = findViewById(R.id.DateDropDownSpinner);
        searchBtn=findViewById(R.id.SearchButton);
        list=findViewById(R.id.LV);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(AppointmentSearch.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.WeekDays));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myDateSpinner.setAdapter(spinnerAdapter);

        myDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String day= myDateSpinner.getSelectedItem().toString();
                dayOfWeek=dayIndex(day);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                dayOfWeek=0;
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                searchTextString=searchText.getText().toString();
                if(searchTextString.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter Term to Search", Toast.LENGTH_SHORT).show();
                }else{
                    if(address.isChecked()){
                        SF=new SearchFilter("address");
                    }else if(service.isChecked()){
                        SF=new SearchFilter("service");
                    }else if(wh.isChecked()){
                        SF=new SearchFilter("workingHours");
                    }else{
                        Toast.makeText(getApplicationContext(), "Select a Filter to Search", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        //SearchAdapter adapter = new SearchAdapter();
        //list.setAdapter(adapter);

    }

    private int dayIndex(String day){
        int i;
        switch (day){
            case "Monday": i=0;
            break;
            case "Tuesday": i=1;
            break;
            case "Wednesday": i=2;
            break;
            case "Thursday": i=3;
            break;
            case "Friday": i=4;
            break;
            case "Saturday": i=5;
            break;
            case "Sunday": i=6;
            break;
            default: i=-1;
        }
        return i;
    }

}
