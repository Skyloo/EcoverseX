package com.example.ecoversex.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;

public class AdminViewSubmissionActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_submission);

        firebaseAuth = FirebaseAuth.getInstance();
    }
}
