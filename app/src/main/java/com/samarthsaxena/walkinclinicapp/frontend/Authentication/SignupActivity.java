package com.samarthsaxena.walkinclinicapp.frontend.Authentication;

import com.samarthsaxena.walkinclinicapp.R;
import com.samarthsaxena.walkinclinicapp.backend.facades.Authentication;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.models.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private RadioButton patientOption;
    private RadioButton employeeOption;
    private EditText usernameText;
    private EditText emailText;
    private EditText passwordText;
    private EditText rpasswordText;
    private Button signupButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        patientOption = findViewById(R.id.patient);
        employeeOption = findViewById(R.id.employee);
        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        rpasswordText = findViewById(R.id.rpasswordText);
        signupButton = findViewById(R.id.loginButton);
        emailText = findViewById(R.id.emailText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Obtain fields from UI
                final String username = usernameText.getText().toString();
                final String pass = passwordText.getText().toString();
                final String rpass = rpasswordText.getText().toString();
                final String email = emailText.getText().toString();
                String typesel = "";

                // Check fields aren't empty
                if (username.isEmpty() || pass.isEmpty() || email.isEmpty()) {
                    sendMessage("Empty fields aren't allowed!");
                    return;
                }

                // Check passwords are the same
                if (!pass.equals(rpass)) {
                    sendMessage("Passwords don't match!");
                    return;
                }

                // Check password is minimum of 8 characters
                if (pass.length() < 8) {
                    sendMessage("Password must be at least 8 characters!");
                    return;
                }

                // Determine user type
                if (employeeOption.isChecked()) {
                    typesel = "employee";
                } else {
                    typesel = "patient";
                }

                // Register user
                Authentication.register(email, username, pass, typesel, new MyCallback() {

                    @Override
                    public void onCallback(Object value) {

                        User user = (User) value;
                        sendMessage("User " + user.getUsername() + " successfully registered.");
                        // Go back to main activity
                        Intent myIntent = new Intent(SignupActivity.this, MainActivity.class);
                        SignupActivity.this.startActivity(myIntent);
                    }

                    @Override
                    public void exceptionHandler(String message) {
                        sendMessage("User registration error: " + message);
                    }
                });

            }
        });

    }

    public void sendMessage(String message) {
        Toast.makeText(SignupActivity.this, message, Toast.LENGTH_LONG).show();
    }
}

