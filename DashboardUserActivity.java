package com.example.udmenglishmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.udmenglishmaster.databinding.ActivityDashboardUserBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardUserActivity extends AppCompatActivity {

    // View binding
    private ActivityDashboardUserBinding binding;

    // Firebase Auth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardUserBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();

        // Handle logout button click
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                checkUser();
            }
        });

        // Handle manage courses button click
        binding.CoursesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardUserActivity.this, CoursesActivity.class));
            }
        });

        // Handle manage quizzes button click
        binding.QuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardUserActivity.this, QuizzActivity.class));
            }
        });

        //Handle chatbot button
        binding.ChatBotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardUserActivity.this, AIChatBotActivity.class));
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get proficiency level from Intent and update UI
        String proficiencyLevel = getProficiencyLevelFromIntent();
        Log.d("DashboardUserActivity", "Proficiency Level: " + proficiencyLevel);
        if (proficiencyLevel != null && !proficiencyLevel.isEmpty()) {
            updateProficiency(proficiencyLevel);
        } else {
            Log.d("DashboardUserActivity", "Proficiency level is null or empty.");
        }
    }

    private void checkUser() {
        // Get current user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            // Not logged in, redirect to login screen
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            // Logged in, get user info
            String email = firebaseUser.getEmail();
            // Set in TextView of toolbar
            binding.subTitleTv.setText(email);
        }
    }

    private String getProficiencyLevelFromIntent() {
        // Assuming proficiency level is passed via intent
        Intent intent = getIntent();
        return intent.getStringExtra("proficiencyLevel");
    }

    private void updateProficiency(String proficiencyLevel) {
        // Set proficiency level text
        binding.proficiencyLevelTv.setText("Proficiency level: " + proficiencyLevel);

        // Update the icon based on proficiency level
        switch (proficiencyLevel) {
            case "Pro":
                binding.proficiencyIcon.setImageResource(R.drawable.baseline_star_gold_24);
                break;
            case "Intermediate":
                binding.proficiencyIcon.setImageResource(R.drawable.baseline_star_silver_24);
                break;
            case "Beginner":
                binding.proficiencyIcon.setImageResource(R.drawable.baseline_star_bronze_24);
                break;
            default:
                binding.proficiencyIcon.setImageResource(R.drawable.baseline_star_24); // Fallback icon
                break;
        }
    }
}
