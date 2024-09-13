package com.example.udmenglishmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionPage1Activity extends AppCompatActivity {

    private RadioGroup answerGroup1, answerGroup2;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page1);

        answerGroup1 = findViewById(R.id.answerGroup1);
        answerGroup2 = findViewById(R.id.answerGroup2);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnyOptionSelected()) {
                    Intent intent = new Intent(QuestionPage1Activity.this, QuestionPage2Activity.class);
                    intent.putExtra("answer1", answerGroup1.getCheckedRadioButtonId());
                    intent.putExtra("answer2", answerGroup2.getCheckedRadioButtonId());
                    startActivity(intent);
                } else {
                    Toast.makeText(QuestionPage1Activity.this, "Please answer all questions.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isAnyOptionSelected() {
        return answerGroup1.getCheckedRadioButtonId() != -1 && answerGroup2.getCheckedRadioButtonId() != -1;
    }
}
