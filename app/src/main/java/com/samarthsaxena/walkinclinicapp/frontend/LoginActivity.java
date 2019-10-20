package com.samarthsaxena.walkinclinicapp.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.samarthsaxena.walkinclinicapp.R;
import com.samarthsaxena.walkinclinicapp.backend.Authentication;
import com.samarthsaxena.walkinclinicapp.backend.MyCallback;
import com.samarthsaxena.walkinclinicapp.backend.models.User;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameText;
    private EditText passwordText;
    private Button login;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.loginButton);
        signup = findViewById(R.id.signupButton);

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = usernameText.getText().toString();
                final String password = passwordText.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    sendMessage("Empty fields aren't allowed!");
                    return;
                }

                Authentication.login(username, password, new MyCallback() {
                    @Override
                    public void onCallback(Object value) {
                        User user = (User) value;
                        sendMessage("User " + user.getUsername() + " succesfully logged in.");
                        Intent myIntent = new Intent(LoginActivity.this, WelcomeActivity.class);
                        LoginActivity.this.startActivity(myIntent);
                    }

                    @Override
                    public void exceptionHandler(String message) {

                        sendMessage(message);
                    }
                });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to sign up activity
                Intent myIntent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(myIntent);
            }
        });
    }

    public void sendMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
