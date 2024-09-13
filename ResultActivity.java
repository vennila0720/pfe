package com.example.udmenglishmaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;

public class ResultActivity extends Activity {

    private TextView levelText;
    private TextView levelDescription;
    private ImageView levelImage;
    private Button suggestionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result); // Ensure this matches your XML layout file name

        // Initialize views
        levelText = findViewById(R.id.proficiencyTitle);
        levelDescription = findViewById(R.id.proficiencyResult);
        levelImage = findViewById(R.id.proficiencyPopup); // Initialize the ImageView for the smiley
        suggestionButton = findViewById(R.id.goToDashboardButton);

        // Retrieve answers from the Intent
        Intent intent = getIntent();
        int answer1 = intent.getIntExtra("answer1", -1);
        int answer2 = intent.getIntExtra("answer2", -1);
        int answer3 = intent.getIntExtra("answer3", -1);
        int answer4 = intent.getIntExtra("answer4", -1);
        int answer5 = intent.getIntExtra("answer5", -1);

        // Calculate proficiency level based on answers
        String proficiencyLevel = determineProficiencyLevel(answer1, answer2, answer3, answer4, answer5);

        // Update the proficiency UI
        switch (proficiencyLevel) {
            case "Beginner":
                levelText.setText("Beginner");
                levelDescription.setText("You're just starting out. Keep practicing and you'll improve in no time.");
                levelImage.setImageResource(R.drawable.baseline_sentiment_beginner_24);
                suggestionButton.setText("Learn English");
                break;
            case "Intermediate":
                levelText.setText("Intermediate");
                levelDescription.setText("You're making good progress. Consider exploring more advanced materials.");
                levelImage.setImageResource(R.drawable.baseline_sentiment_intermediate_24);
                suggestionButton.setText("Learn English");
                break;
            case "Pro":
                levelText.setText("Pro");
                levelDescription.setText("You're quite proficient. Keep up the good work and continue to challenge yourself.");
                levelImage.setImageResource(R.drawable.baseline_insert_pro_emoticon_24);
                suggestionButton.setText("Learn English");
                break;
            default:
                levelText.setText("Unknown");
                levelDescription.setText("An error occurred. Please try again.");
                levelImage.setImageResource(R.drawable.error); // Use an appropriate drawable
                suggestionButton.setText("Learn English");
                break;
        }

        // Save proficiency level in SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("proficiencyLevel", proficiencyLevel); // Save proficiency level
        editor.putBoolean("selfAssessmentCompleted", true);
        editor.apply();

        // Set up button click listener
        suggestionButton.setOnClickListener(v -> {
            // Create an Intent to start DashboardUserActivity
            Intent dashboardIntent = new Intent(ResultActivity.this, DashboardUserActivity.class);
            dashboardIntent.putExtra("proficiencyLevel", proficiencyLevel); // Pass proficiency level
            startActivity(dashboardIntent);
            finish(); // Close ResultActivity
        });
    }

    private String determineProficiencyLevel(int answer1, int answer2, int answer3, int answer4, int answer5) {
        // Implement your logic to determine proficiency level based on answers
        int totalScore = 0;
        totalScore += getScoreFromAnswer(answer1);
        totalScore += getScoreFromAnswer(answer2);
        totalScore += getScoreFromAnswer(answer3);
        totalScore += getScoreFromAnswer(answer4);
        totalScore += getScoreFromAnswer(answer5);

        // Determine level based on totalScore
        if (totalScore <= 5) {
            return "Beginner";
        } else if (totalScore <= 10) {
            return "Intermediate";
        } else {
            return "Pro";
        }
    }

    private int getScoreFromAnswer(int answerId) {
        // Map the answer ID to a score using if-else statements
        if (answerId == R.id.answerOption1_1 ||
                answerId == R.id.answerOption2_1 ||
                answerId == R.id.answerOption3_1 ||
                answerId == R.id.answerOption4_1 ||
                answerId == R.id.answerOption5_1) {
            return 1;
        } else if (answerId == R.id.answerOption1_2 ||
                answerId == R.id.answerOption2_2 ||
                answerId == R.id.answerOption3_2 ||
                answerId == R.id.answerOption4_2 ||
                answerId == R.id.answerOption5_2) {
            return 2;
        } else if (answerId == R.id.answerOption1_3 ||
                answerId == R.id.answerOption2_3 ||
                answerId == R.id.answerOption3_3 ||
                answerId == R.id.answerOption4_3 ||
                answerId == R.id.answerOption5_3) {
            return 3;
        } else if (answerId == R.id.answerOption1_4 ||
                answerId == R.id.answerOption2_4 ||
                answerId == R.id.answerOption3_4 ||
                answerId == R.id.answerOption4_4 ||
                answerId == R.id.answerOption5_4) {
            return 4;
        } else {
            return 0;
        }
    }
}
