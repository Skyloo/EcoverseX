package com.example.ecoversex.RecyclerActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ecoversex.GeneralActivity.MainActivity;
import com.example.ecoversex.GeneralActivity.ProfileActivity;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;

public class RecyclerActivity extends AppCompatActivity {

    Button recycler_view_material_button, recycler_view_submission_button, recycler_logout_button ,recycler_redemption_button;

    //Add Database Stuff
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        firebaseAuth = FirebaseAuth.getInstance();

        //Components
        recycler_view_material_button = (Button) findViewById(R.id.recycler_view_material_button);
        recycler_view_submission_button = (Button) findViewById(R.id.recycler_view_submission_button);
        recycler_logout_button = (Button) findViewById(R.id.recycler_logout_button);
        recycler_redemption_button = (Button) findViewById(R.id.recycler_redemption_button);

        //Button Functions
        recycler_view_material_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RecyclerViewMaterialActivity.class));
                Toast.makeText(getApplicationContext(), " View Material Page", Toast.LENGTH_SHORT).show();
            }
        });

        recycler_view_submission_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RecyclerViewSubmissionActivity.class));
                Toast.makeText(getApplicationContext(), "View Submission Page", Toast.LENGTH_SHORT).show();
            }
        });

        recycler_logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Logout Successful!", Toast.LENGTH_SHORT).show();
            }
        });

        recycler_redemption_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RecyclerRedemptionPage.class));
                Toast.makeText(getApplicationContext(), "Redemption Page.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
