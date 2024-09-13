package com.example.udmenglishmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.udmenglishmaster.QuestionPage1Activity;

public class SelfAssessmentActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_assessment);

        startButton = findViewById(R.id.startButton);

        // Set a click listener for the start button
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the first set of questions
                Intent intent = new Intent(SelfAssessmentActivity.this, QuestionPage1Activity.class);
                startActivity(intent);
            }
        });
    }
}
