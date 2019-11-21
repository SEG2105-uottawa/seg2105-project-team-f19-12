package com.samarthsaxena.walkinclinicapp.frontend.Employee;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.samarthsaxena.walkinclinicapp.R;

public class DeleteServiceEmployeeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_deleteemployeeservice);
        final String username = getIntent().getStringExtra("EXTRA_USERNAME");
    }

}
