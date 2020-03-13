package com.example.ecoversex.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ecoversex.GeneralActivity.MainActivity;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {

    Button admin_profile_button, admin_view_material_button, admin_view_submission_button, admin_logout_button;

    //Add Database Stuff
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        firebaseAuth = FirebaseAuth.getInstance();


        admin_profile_button = (Button)findViewById(R.id.admin_profile_button);
        admin_view_material_button = (Button) findViewById(R.id.admin_view_material_button);
        admin_view_submission_button = (Button) findViewById(R.id.admin_view_submission_button);
        admin_logout_button = (Button) findViewById(R.id.admin_logout_button);



        admin_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminProfileActivity.class));
                Toast.makeText(getApplicationContext(), "Admin Profile Page", Toast.LENGTH_SHORT).show();
            }
        });

        admin_view_material_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminViewMaterialActivity.class));
                Toast.makeText(getApplicationContext(), " View Material Page", Toast.LENGTH_SHORT).show();
            }
        });

        admin_view_submission_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminViewSubmissionActivity.class));
                Toast.makeText(getApplicationContext(), "View Submission Page", Toast.LENGTH_SHORT).show();
            }
        });

        admin_logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Logout Successful!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
