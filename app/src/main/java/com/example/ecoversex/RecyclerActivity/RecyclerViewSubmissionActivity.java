package com.example.ecoversex.RecyclerActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ecoversex.HelperClass.Material;
import com.example.ecoversex.HelperClass.Submission;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecyclerViewSubmissionActivity extends AppCompatActivity {

    public static final String SUBMISSION_ID = "submissionID";
    public static final String MATERIAL_NAME = "materialname";
    public static final String PROPOSED_DATE = "proposedDate";
    public static final String SUBMISSION_WEIGHT= "weight";

    ListView submission_list;

    Submission submission;
    Material material;
    String userID;

    //Firebase
    DatabaseReference submissionRef;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    //Database ArrayList
    ArrayList<Submission> submissionList;
    ArrayAdapter<Submission> submissionAdapter;

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_submission);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        submission_list = (ListView) findViewById(R.id.submission_list);

        firebaseDatabase = FirebaseDatabase.getInstance();
        submissionRef = firebaseDatabase.getReference("submission").child(userID);

        submission = new Submission();
        submissionList = new ArrayList<>();
        submissionAdapter = new ArrayAdapter<Submission>(this,android.R.layout.simple_list_item_1,submissionList);

        submissionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot subsnapshot : dataSnapshot.getChildren()){

                            submission = subsnapshot.getValue(Submission.class);
                            submissionList.add(submission);
                        }
                        submission_list.setAdapter(submissionAdapter);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        submission_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Submission submission = submissionList.get(position);
                Intent intent = new Intent(getApplicationContext(), EditSubmissionDialogActivity.class);

                intent.putExtra(SUBMISSION_ID, submission.getSubmissionID());
                intent.putExtra(PROPOSED_DATE, submission.getProposedDate());
                intent.putExtra(MATERIAL_NAME, submission.getMaterialName());
                intent.putExtra(SUBMISSION_WEIGHT, submission.getWeight());

                startActivity(intent);

            }
        });



    }
}
