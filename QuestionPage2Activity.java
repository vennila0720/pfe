package com.example.udmenglishmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionPage2Activity extends AppCompatActivity {

    private RadioGroup answerGroup3, answerGroup4;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page2);

        answerGroup3 = findViewById(R.id.answerGroup3);
        answerGroup4 = findViewById(R.id.answerGroup4);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnyOptionSelected()) {
                    Intent intent = new Intent(QuestionPage2Activity.this, QuestionPage3Activity.class);
                    intent.putExtra("answer3", answerGroup3.getCheckedRadioButtonId());
                    intent.putExtra("answer4", answerGroup4.getCheckedRadioButtonId());
                    startActivity(intent);
                } else {
                    Toast.makeText(QuestionPage2Activity.this, "Please answer all questions.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isAnyOptionSelected() {
        return answerGroup3.getCheckedRadioButtonId() != -1 && answerGroup4.getCheckedRadioButtonId() != -1;
    }
}
