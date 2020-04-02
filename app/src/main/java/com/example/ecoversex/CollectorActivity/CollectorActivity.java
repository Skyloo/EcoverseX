package com.example.ecoversex.CollectorActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ecoversex.GeneralActivity.MainActivity;
import com.example.ecoversex.R;
import com.example.ecoversex.RecyclerActivity.RecyclerProfileActivity;
import com.google.firebase.auth.FirebaseAuth;

public class CollectorActivity extends AppCompatActivity {

    Button collector_profile_button, collector_proposed_submission_button, collector_view_submission_button, collector_logout_button;

    //Add Database Stuff
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collector);

        firebaseAuth = FirebaseAuth.getInstance();

        //Components
        collector_profile_button = (Button) findViewById(R.id.collector_profile_button);
        collector_proposed_submission_button = (Button) findViewById(R.id.collector_proposed_submission_button);
        collector_view_submission_button = (Button) findViewById(R.id.collector_view_submission_button);
        collector_logout_button = (Button) findViewById(R.id.collector_logout_button);

        //Button Functions
        collector_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), RecyclerProfileActivity.class));
                Toast.makeText(getApplicationContext(), "Collector Profile Page", Toast.LENGTH_LONG).show();
            }
        });

        collector_proposed_submission_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), CollectorViewProposedSubmissionActivity.class));
                Toast.makeText(getApplicationContext(), "View Proposed Submission Page", Toast.LENGTH_LONG).show();
            }
        });

        collector_view_submission_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),CollectorViewSubmissionActivity.class));
                Toast.makeText(getApplicationContext(), "View All Submission Page", Toast.LENGTH_LONG).show();
            }
        });

        collector_logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_LONG).show();
            }
        });

    }
}
