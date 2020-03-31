package com.example.ecoversex.RecyclerActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecoversex.HelperClass.Recycler;
import com.example.ecoversex.HelperClass.Submission;
import com.example.ecoversex.HelperClass.User;
import com.example.ecoversex.R;
import com.example.ecoversex.RecyclerActivity.RecyclerViewMaterialActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class ProposeDialogActivity extends AppCompatActivity {

    EditText propose_date_edittext,actual_date_edittext,weight_edittext;
    Button confirm_submission_button;
    TextView propose_material_name_textview,propose_material_id_textview,propose_material_point_textview;
    String userID;

    private FirebaseAuth firebaseAuth;
    DatabaseReference subReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propose_dialog);

        firebaseAuth = FirebaseAuth.getInstance();

        propose_date_edittext = (EditText) findViewById(R.id.propose_date_edittext);
        actual_date_edittext = (EditText) findViewById(R.id.actual_date_edittext);
        weight_edittext = (EditText) findViewById(R.id.weight_edittext);
        confirm_submission_button = (Button) findViewById(R.id.confirm_submission_button);
        propose_material_name_textview = (TextView) findViewById(R.id.propose_material_name_textview);
        propose_material_id_textview = (TextView) findViewById(R.id.propose_material_id_textview);
        propose_material_point_textview = (TextView) findViewById(R.id.propose_material_point_textview);


        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        Intent intent = getIntent();

        final String materialName = intent.getStringExtra(RecyclerViewMaterialActivity.MATERIAL_NAME);
        final String materialId = intent.getStringExtra(RecyclerViewMaterialActivity.MATERIAL_ID);
        final String pointPerKG = intent.getStringExtra(RecyclerViewMaterialActivity.POINT_PER_KG);

        propose_material_name_textview.setText(materialName);
        propose_material_id_textview.setText(materialId);
        propose_material_point_textview.setText(pointPerKG + "points");

        //Connecting submission to materialId
        subReference = FirebaseDatabase.getInstance().getReference("submission").child(userID);

        confirm_submission_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String proposeDate = propose_date_edittext.getText().toString().trim();
                String actualDate = actual_date_edittext.getText().toString().trim();
                String weight = weight_edittext.getText().toString().trim();
                String status = "proposed";
                int pointAwarded;


                if(!propose_date_edittext.equals(null) && (!actual_date_edittext.equals(null) && (!weight_edittext.equals(null)))){
                    String id  = subReference.push().getKey();
                    int w = Integer.parseInt(weight);
                    final int ppg = Integer.parseInt(pointPerKG);
                    pointAwarded = (w*ppg);
                    String point = Integer.toString(pointAwarded);

                    Submission submission = new Submission(id, proposeDate, actualDate,materialName, weight, point, status);

                    subReference.child(id).setValue(submission);

                    Toast.makeText(getApplicationContext(),"Submission Proposed", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(getApplicationContext(), RecyclerViewMaterialActivity.class));
                }
                else{
                    if (TextUtils.isEmpty(proposeDate)){
                        Toast.makeText(getApplicationContext(), "Please key in proposal date of submission...", Toast.LENGTH_LONG).show();
                    }
                    if (TextUtils.isEmpty(actualDate)){
                        Toast.makeText(getApplicationContext(), "Please key in actual date of submission...", Toast.LENGTH_LONG).show();
                    }
                    if (TextUtils.isEmpty(weight)){
                        Toast.makeText(getApplicationContext(), "Please key in weight of submission...", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }
}
