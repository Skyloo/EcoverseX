package com.example.ecoversex.RecyclerActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecoversex.HelperClass.Submission;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EditSubmissionDialogActivity extends AppCompatActivity {

    TextView edit_submissionid_textview, edit_proposedDate_textview, edit_weight_textview, edit_materialName_textview;
    Button edit_submission_delete_button;
    String userID;

    private FirebaseAuth firebaseAuth;
    DatabaseReference subReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_submission_dialog);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        edit_submission_delete_button = (Button) findViewById(R.id.edit_submission_delete_button);
        edit_submissionid_textview = (TextView) findViewById(R.id.edit_submissionid_textview);
        edit_proposedDate_textview = (TextView) findViewById(R.id. edit_proposedDate_textview);
        edit_materialName_textview = (TextView) findViewById(R.id.edit_materialName_textview);
        edit_weight_textview = (TextView) findViewById(R.id.edit_weight_textview);

        Intent intent = getIntent();

        final String submissionId = intent.getStringExtra(RecyclerViewSubmissionActivity.SUBMISSION_ID);
        final String proposedDate = intent.getStringExtra(RecyclerViewSubmissionActivity.PROPOSED_DATE);
        final String materialName = intent.getStringExtra(RecyclerViewSubmissionActivity.MATERIAL_NAME);
        final String submissionWeight = intent.getStringExtra(RecyclerViewSubmissionActivity.SUBMISSION_WEIGHT);

        edit_submissionid_textview.setText(submissionId);
        edit_proposedDate_textview .setText(proposedDate);
        edit_materialName_textview.setText(materialName);
        edit_weight_textview.setText(submissionWeight);

        //Connecting submission to materialId
        subReference = FirebaseDatabase.getInstance().getReference("submission").child(userID);

        edit_submission_delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                subReference.child(submissionId).removeValue();

                Toast.makeText(getApplicationContext(), "Submission successfully deleted." ,Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), RecyclerActivity.class));
            }
        });



    }
}
