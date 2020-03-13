package com.example.ecoversex.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;

public class AdminViewSubmissionActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    Button view_submission_back_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_submission);

        view_submission_back_button = (Button) findViewById(R.id.view_submission_back_button);

        firebaseAuth = FirebaseAuth.getInstance();

        view_submission_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_LONG).show();
            }
        });
    }
}
