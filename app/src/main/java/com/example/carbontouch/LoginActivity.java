package com.example.carbontouch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                // get username and password from text fields
                String username = ((TextView) findViewById(R.id.username_edit_text)).getText().toString();
                String password = ((TextView) findViewById(R.id.password_edit_text)).getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    // toast error message
                    Toast.makeText(LoginActivity.this, "Please enter a username and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                // login user
                carbonDBHelper db = new carbonDBHelper(LoginActivity.this);
                if (db.checkUser(username, password)) {
                    // return result to previous activity
                    Intent intent = new Intent();
                    intent.putExtra("username", username);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    // toast error message
                    Toast.makeText(LoginActivity.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
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

}

