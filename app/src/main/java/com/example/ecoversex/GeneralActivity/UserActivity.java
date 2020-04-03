package com.example.ecoversex.GeneralActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ecoversex.CollectorActivity.CollectorActivity;
import com.example.ecoversex.R;
import com.example.ecoversex.RecyclerActivity.RecyclerActivity;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {

    Button user_profile_button,recycler_button, collector_button, user_logout_button;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        firebaseAuth = FirebaseAuth.getInstance();

        user_profile_button = (Button) findViewById(R.id.user_profile_button);
        recycler_button = (Button) findViewById(R.id.recycler_button);
        collector_button = (Button) findViewById(R.id.collector_button);
        user_logout_button = (Button) findViewById(R.id.user_logout_button);

        user_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                Toast.makeText(getApplicationContext(), "Profile Page.", Toast.LENGTH_LONG).show();
            }
        });

        recycler_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RecyclerActivity.class));
                Toast.makeText(getApplicationContext(), "Recycler Page.", Toast.LENGTH_SHORT).show();

            }
        });

        collector_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CollectorActivity.class));
                Toast.makeText(getApplicationContext(), "Collector Page.", Toast.LENGTH_SHORT).show();
            }
        });

        user_logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Logout Successful!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
