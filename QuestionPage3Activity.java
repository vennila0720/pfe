package com.example.udmenglishmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionPage3Activity extends AppCompatActivity {

    private RadioGroup answerGroup5;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page3);

        answerGroup5 = findViewById(R.id.answerGroup5);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnyOptionSelected()) {
                    Intent intent = new Intent(QuestionPage3Activity.this, ResultActivity.class);
                    intent.putExtra("answer5", answerGroup5.getCheckedRadioButtonId());

                    // Get answers from previous activities
                    Intent previousIntent = getIntent();
                    intent.putExtra("answer1", previousIntent.getIntExtra("answer1", -1));
                    intent.putExtra("answer2", previousIntent.getIntExtra("answer2", -1));
                    intent.putExtra("answer3", previousIntent.getIntExtra("answer3", -1));
                    intent.putExtra("answer4", previousIntent.getIntExtra("answer4", -1));

                    startActivity(intent);
                } else {
                    Toast.makeText(QuestionPage3Activity.this, "Please answer the question.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isAnyOptionSelected() {
        return answerGroup5.getCheckedRadioButtonId() != -1;
    }
}
