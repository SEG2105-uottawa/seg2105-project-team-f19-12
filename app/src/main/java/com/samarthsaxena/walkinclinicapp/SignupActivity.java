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
    class User{
        String name="";
        String pass="";
        String rpass="";
        String typesel="";


        User(String name,String pass,String rpass,String typesel){
            this.name=name;
            this.pass=pass;
            this.rpass=rpass;
            this.typesel=typesel;
        }
    }
    DatabaseReference usersRef;
    RadioButton patient;
    RadioButton employee;
    EditText usernameText;
    EditText passwordText;
    EditText rpasswordText;
    Button loginButton;
FirebaseAuth firebaseAuth;
    DatabaseReference userregister;
     String type="";
     int count=0;




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

        if(patient.isChecked()){
            type=patient.getText().toString();
        }
        else if(employee.isChecked())
            type=employee.getText().toString();
        else{

        }

firebaseAuth= FirebaseAuth.getInstance();


loginButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        firebaseAuth.createUserWithEmailAndPassword(usernameText.getText().toString(), passwordText.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    String name=usernameText.getText().toString();
                    String pass=passwordText.getText().toString();
                    String rpass=rpasswordText.getText().toString();
                    String typesel=type;
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            userregister = FirebaseDatabase.getInstance().getReference();
                            usersRef = userregister.child("users");

                            Map<String,User> users = new HashMap<>();
                            users.put("Registered", new User(name,pass,rpass,typesel));

                            usersRef.setValue(users);

                            Toast.makeText(SignupActivity.this,"User is successfully registered",Toast.LENGTH_LONG).show();


                        } else {
//                            Toast.makeText(SignupActivity.this,task.getException().getMessage(),
//                                    Toast.LENGTH_SHORT).show();

                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }
});
    }

}
