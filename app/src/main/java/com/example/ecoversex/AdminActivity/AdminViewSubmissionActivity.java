package com.example.ecoversex.AdminActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ecoversex.HelperClass.CompletedSubmission;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminViewSubmissionActivity extends AppCompatActivity {

    ListView admin_submission_list;
    CompletedSubmission completedSubmission;

    //Firebase
    DatabaseReference submissionRef;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    //Database ArrayList
    ArrayList<CompletedSubmission> submissionList;
    ArrayAdapter<CompletedSubmission> submissionAdapter;


    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_submission);

        firebaseAuth = FirebaseAuth.getInstance();

        admin_submission_list = (ListView) findViewById(R.id.admin_submission_list);

        firebaseDatabase = FirebaseDatabase.getInstance();
        submissionRef = firebaseDatabase.getReference("completed_submission");

        completedSubmission = new CompletedSubmission();
        submissionList = new ArrayList<>();
        submissionAdapter = new ArrayAdapter<CompletedSubmission>(this,android.R.layout.simple_list_item_1,submissionList);

        submissionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot subsnapshot : dataSnapshot.getChildren()){

                    completedSubmission = subsnapshot.getValue(CompletedSubmission.class);
                    submissionList.add(completedSubmission);
                }
                admin_submission_list.setAdapter(submissionAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
