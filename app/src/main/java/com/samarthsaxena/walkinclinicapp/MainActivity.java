package com.samarthsaxena.walkinclinicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button move;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

move=findViewById(R.id.move);

//        Intent myIntent1 = new Intent(this, SignupActivity.class);
//        startActivity(myIntent1);

        move.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        SignupActivity.class);
                startActivity(myIntent);
            }
        });
    }
    }

