package com.example.udmenglishmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if IntroActivity has been shown before
        if (!restorePrefData()) {
            Intent introIntent = new Intent(MainActivity.this, IntroActivity.class);
            startActivity(introIntent);
            finish(); // Close MainActivity
        } else {
            // User has seen the intro before, go directly to LoginActivity
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish(); // Close MainActivity
        }
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        return pref.getBoolean("isIntroOpened", false);
    }

}

