package com.example.ecoversex.CollectorActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ecoversex.HelperClass.CSubmission;
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

public class CollectorViewProposedSubmissionActivity extends AppCompatActivity {

    public static final String SUBMISSION_ID = "submissionID";
    public static final String MATERIAL_NAME = "materialname";
    public static final String PROPOSED_DATE = "proposedDate";
    public static final String SUBMISSION_WEIGHT= "weight";
    public static final String STATUS= "status";
    public static final String USERID = "userID";
    public static final String POINT = "point";
    public static final String ACTUAL_DATE = "actualDate";

    ListView collector_proposed_submission_list;

    CSubmission cSubmission;
    Material material;
    String userID;

    //Firebase
    DatabaseReference cSubmissionRef;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    //Database ArrayList
    ArrayList<CSubmission> cSubmissionList;
    ArrayAdapter<CSubmission> cSubmissionAdapter;

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collector_view_proposed_submission);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        collector_proposed_submission_list = (ListView) findViewById(R.id.collector_proposed_submission_list);

        firebaseDatabase = FirebaseDatabase.getInstance();
        cSubmissionRef = firebaseDatabase.getReference("proposed_submission");

        cSubmission = new CSubmission();
        cSubmissionList = new ArrayList<>();
        cSubmissionAdapter = new ArrayAdapter<CSubmission>(this, android.R.layout.simple_list_item_1, cSubmissionList);

        cSubmissionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot subsnapshot : dataSnapshot.getChildren()){

                    cSubmission = subsnapshot.getValue(CSubmission.class);
                    cSubmissionList.add(cSubmission);
                }
                collector_proposed_submission_list.setAdapter(cSubmissionAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        collector_proposed_submission_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CSubmission cSubmission = cSubmissionList.get(position);
                Intent intent = new Intent(getApplicationContext(), ConfirmSubmissionDialogActivity.class);

                intent.putExtra(SUBMISSION_ID, cSubmission.getSubmissionID());
                intent.putExtra(PROPOSED_DATE, cSubmission.getProposedDate());
                intent.putExtra(MATERIAL_NAME, cSubmission.getMaterialName());
                intent.putExtra(SUBMISSION_WEIGHT, cSubmission.getWeight());
                intent.putExtra(STATUS, cSubmission.getStatus());
                intent.putExtra(USERID, cSubmission.getUserID());
                intent.putExtra(POINT, cSubmission.getPointAwarded());
                intent.putExtra(ACTUAL_DATE, cSubmission.getActualDate());

                startActivity(intent);

            }
        });
    }
}
