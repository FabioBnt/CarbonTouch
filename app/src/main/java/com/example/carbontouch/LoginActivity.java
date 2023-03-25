package com.example.carbontouch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button cancelButton;
    private TextView signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_button);
        cancelButton = findViewById(R.id.cancel_button);
        signUpButton = findViewById(R.id.sign_up_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if user is connected or not
                boolean isConnected = checkConnectionStatus();

                if (isConnected) {
                    // TODO: return to previous page with the value of the user
                    finish();
                } else {
                    // If user is not connected, display error message
                    showError();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cancel login and return to previous page
                finish();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new intent to sign up page
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean checkConnectionStatus() {
        // Check connection status of user
        // TODO: Implement connection status check

        return true;
    }

    private void showError() {
        // Display error message to user
        // TODO: Implement error message
    }
}

