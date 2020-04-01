package com.example.ecoversex.CollectorActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecoversex.HelperClass.CSubmission;
import com.example.ecoversex.HelperClass.CompletedSubmission;
import com.example.ecoversex.HelperClass.Submission;
import com.example.ecoversex.HelperClass.User;
import com.example.ecoversex.R;
import com.example.ecoversex.RecyclerActivity.RecyclerViewSubmissionActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConfirmSubmissionDialogActivity extends AppCompatActivity {

    Button confirm_submission_button;
    TextView confirm_submissionid_textview, confirm_proposedDate_textview, confirm_materialName_textview, confirm_weight_textview, confirm_status_textview,confirm_userid_textview;

    //Firebase
    DatabaseReference cSubmissionRef;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    DatabaseReference subReference;
    DatabaseReference collectorSubRef;
    DatabaseReference completedSubRef;
    DatabaseReference userRef;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_submission_dialog);

        firebaseAuth = FirebaseAuth.getInstance();

        confirm_submission_button = (Button) findViewById(R.id.confirm_submission_button);
        confirm_submissionid_textview = (TextView) findViewById(R.id.confirm_submissionid_textview);
        confirm_proposedDate_textview = (TextView) findViewById(R.id.confirm_proposedDate_textview);
        confirm_materialName_textview = (TextView) findViewById(R.id.confirm_materialName_textview);
        confirm_weight_textview = (TextView) findViewById(R.id.confirm_weight_textview);
        confirm_status_textview = (TextView)findViewById(R.id.confirm_status_textview);
        confirm_userid_textview = (TextView) findViewById(R.id.confirm_userid_textview);

        Intent intent = getIntent();

        final String submissionId = intent.getStringExtra(CollectorViewProposedSubmissionActivity.SUBMISSION_ID);
        final String proposedDate = intent.getStringExtra(CollectorViewProposedSubmissionActivity.PROPOSED_DATE);
        final String actualDate = intent.getStringExtra(CollectorViewProposedSubmissionActivity.ACTUAL_DATE);
        final String materialName = intent.getStringExtra(CollectorViewProposedSubmissionActivity.MATERIAL_NAME);
        final String submissionWeight = intent.getStringExtra(CollectorViewProposedSubmissionActivity.SUBMISSION_WEIGHT);
        final String status = intent.getStringExtra(CollectorViewProposedSubmissionActivity.STATUS);
        final String point = intent.getStringExtra(CollectorViewProposedSubmissionActivity.POINT);
        final String userId = intent.getStringExtra(CollectorViewProposedSubmissionActivity.USERID);

        confirm_submissionid_textview.setText(submissionId);
        confirm_proposedDate_textview.setText(proposedDate);
        confirm_materialName_textview.setText(materialName);
        confirm_weight_textview.setText(submissionWeight);
        confirm_status_textview.setText(status);
        confirm_userid_textview.setText("By User : " + userId);

        //Connecting submission to materialId
        subReference = FirebaseDatabase.getInstance().getReference("submission").child(userId);
        collectorSubRef = FirebaseDatabase.getInstance().getReference("proposed_submission");
        completedSubRef = FirebaseDatabase.getInstance().getReference("completed_submission");
        userRef = FirebaseDatabase.getInstance().getReference("User").child(userId);

        userRef.child("ecoPoint").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String ePoint = dataSnapshot.child("ecoPoint").getValue().toString();
                user.setEcoPoints(ePoint);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //Change the status to "confirmed" and add points to user
        confirm_submission_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = "confirmed";
                final int p = Integer.parseInt(user.getEcoPoints());
                int eP = Integer.parseInt(point);
                int totalPoint = (eP + p);
                String nTotalPoint = Integer.toString(totalPoint);

                Submission submission = new Submission(submissionId, proposedDate, actualDate,materialName, submissionWeight, point, status);
                CSubmission cSubmission = new CSubmission(submissionId, proposedDate, actualDate, materialName, submissionWeight, point, status, userId);
                CompletedSubmission completedSubmission = new CompletedSubmission(submissionId, proposedDate, actualDate, materialName, submissionWeight, point, status, userId);

                subReference.child(submissionId).setValue(submission);
                collectorSubRef.child(submissionId).setValue(cSubmission);
                completedSubRef.child(submissionId).setValue(completedSubmission);
                userRef.child("ecoPoint").setValue(nTotalPoint);

                startActivity(new Intent(getApplicationContext(), CollectorActivity.class));
                Toast.makeText(getApplicationContext(), "Submission confirmed and received.", Toast.LENGTH_LONG).show();


            }
        });

    }
}
