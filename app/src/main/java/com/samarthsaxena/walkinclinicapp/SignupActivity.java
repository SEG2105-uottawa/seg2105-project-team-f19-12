package com.samarthsaxena.walkinclinicapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public class SignupActivity extends AppCompatActivity {

    DatabaseReference usersRef;
    RadioButton patient;
    RadioButton employee;
    EditText usernameText;
    EditText emailText;
    EditText passwordText;
    EditText rpasswordText;
    Button loginButton;
FirebaseAuth firebaseAuth;

     int count=0;
     Member member;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        patient=findViewById(R.id.patient);
        employee=findViewById(R.id.employee);
        usernameText=findViewById(R.id.usernameText);
        passwordText=findViewById(R.id.passwordText);
        rpasswordText=findViewById(R.id.rpasswordText);
        loginButton=findViewById(R.id.loginButton);
        emailText=findViewById(R.id.emailText);
member=new Member();


firebaseAuth= FirebaseAuth.getInstance();
usersRef=FirebaseDatabase.getInstance().getReference().child("Member");

loginButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final String username=usernameText.getText().toString();
        final String pass=passwordText.getText().toString();
        final String rpass=rpasswordText.getText().toString();
        final String email=emailText.getText().toString();
         String typesel="";
        if(patient.isChecked()){
            typesel=patient.getText().toString();
        }
        else if(employee.isChecked())
            typesel=employee.getText().toString();
        else{
            typesel="Admin";
        }
final String type=typesel;
        firebaseAuth.createUserWithEmailAndPassword(emailText.getText().toString().trim(), passwordText.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            member.setEmail(email);

                            member.setUsername(username);
                            member.setPass(pass);
                            member.setRpass(rpass);
                            member.setTypesel(type);
                            usersRef.push().setValue(member);
                            Toast.makeText(SignupActivity.this,"User is successfully registered",Toast.LENGTH_LONG).show();


                        } else {
                            Toast.makeText(SignupActivity.this,task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

//                            Toast.makeText(SignupActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });

    }
});
    }

}
