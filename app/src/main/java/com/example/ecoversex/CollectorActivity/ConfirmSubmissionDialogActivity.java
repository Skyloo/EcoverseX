package com.example.ecoversex.CollectorActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecoversex.R;
import com.example.ecoversex.RecyclerActivity.RecyclerViewSubmissionActivity;

public class ConfirmSubmissionDialogActivity extends AppCompatActivity {

    Button confirm_submission_button;
    TextView confirm_submissionid_textview, confirm_proposedDate_textview, confirm_materialName_textview, confirm_weight_textview, confirm_status_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_submission_dialog);

        confirm_submission_button = (Button) findViewById(R.id.confirm_submission_button);
        confirm_submissionid_textview = (TextView) findViewById(R.id.confirm_submissionid_textview);
        confirm_proposedDate_textview = (TextView) findViewById(R.id.confirm_proposedDate_textview);
        confirm_materialName_textview = (TextView) findViewById(R.id.confirm_materialName_textview);
        confirm_weight_textview = (TextView) findViewById(R.id.confirm_weight_textview);
        confirm_status_textview = (TextView)findViewById(R.id.confirm_status_textview);

        Intent intent = getIntent();

        final String submissionId = intent.getStringExtra(CollectorViewProposedSubmissionActivity.SUBMISSION_ID);
        final String proposedDate = intent.getStringExtra(CollectorViewProposedSubmissionActivity.PROPOSED_DATE);
        final String materialName = intent.getStringExtra(CollectorViewProposedSubmissionActivity.MATERIAL_NAME);
        final String submissionWeight = intent.getStringExtra(CollectorViewProposedSubmissionActivity.SUBMISSION_WEIGHT);
        final String status = intent.getStringExtra(CollectorViewProposedSubmissionActivity.STATUS);

        confirm_submissionid_textview.setText(submissionId);
        confirm_proposedDate_textview.setText(proposedDate);
        confirm_materialName_textview.setText(materialName);
        confirm_weight_textview.setText(submissionWeight);
        confirm_status_textview.setText(status);

        //Change the status to "confirmed" and add points to user
        confirm_submission_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



    }
}
