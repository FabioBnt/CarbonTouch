package com.example.carbontouch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private Button signUpButton;
    private Button cancelButton;
    private TextView loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpButton = findViewById(R.id.sign_up_button);
        cancelButton = findViewById(R.id.cancel_button);
        loginButton = findViewById(R.id.login_button);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get username and password from text fields
                String username = ((TextView) findViewById(R.id.username_edit_text)).getText().toString();
                String password = ((TextView) findViewById(R.id.password_edit_text)).getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    // toast error message
                    Toast.makeText(SignUpActivity.this, "Please enter a username and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                // sign up user
                carbonDBHelper db = new carbonDBHelper(SignUpActivity.this);
                if (db.addUser( username, password)) {
                    // return result to previous activity
                    Intent intent = new Intent();
                    intent.putExtra("username", username);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    // toast error message
                    Toast.makeText(SignUpActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new intent to sign up page
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
