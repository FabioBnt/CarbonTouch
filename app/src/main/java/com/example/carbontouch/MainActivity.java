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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // open the activity "LoginActivity" when the user click on the button "Login"
        Button buttonLogin = (Button) findViewById(R.id.button);
        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
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