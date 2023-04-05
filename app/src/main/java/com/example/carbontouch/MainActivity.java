package com.example.carbontouch;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int LOGIN_REQUEST_CODE = 1;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == RESULT_OK) {
            String username = data.getStringExtra("username");
            // Handle the login result here
            // change button login to profile
            Button loginButton = (Button) findViewById(R.id.button);
            loginButton.setText("Profile");
            // add a listener to the button "Profile" to open the activity "ProfileActivity"
            loginButton.setOnClickListener(v -> {
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // open the activity "LoginActivity" when the user click on the button "Login"
        Button buttonLogin = (Button) findViewById(R.id.button);
        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            try {
                // start activity for result
                startActivityForResult(intent, LOGIN_REQUEST_CODE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // the button should open the activity "TestActivityInstructions"
        Button buttonTestStart = (Button) findViewById(R.id.button2);
        buttonTestStart.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestInstructionsActivity.class);
            startActivity(intent);
        });
    }


    //Generate the menu of the activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    //Handle the menu item click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.language) {
            // TODO: change the language of the app
            return true;
        } else if (id == R.id.profile_menu) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}