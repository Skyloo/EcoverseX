package com.example.ecoversex.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.ecoversex.DatabaseUtil.mDatabaseHandler;
import com.example.ecoversex.HelperClass.Admin;
import com.example.ecoversex.HelperClass.Material;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMaterialActivity extends AppCompatActivity {

    private static final String TAG = "Add or Update to Database";

    private Button material_back_button, material_save_button;
    EditText materialid_input,materialname_input, description_input, pointPerKg_input;
    private String userID;

    //Firebase
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        material_back_button = (Button) findViewById(R.id.material_back_button);
        material_save_button = (Button) findViewById(R.id.material_save_button);
        materialid_input = (EditText) findViewById(R.id.materialid_input);
        materialname_input = (EditText) findViewById(R.id.materialname_input);
        description_input = (EditText) findViewById(R.id.description_input);
        pointPerKg_input = (EditText) findViewById(R.id.pointPerKg_input);

        firebaseAuth = FirebaseAuth.getInstance();

        //Materials using firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                //Whenever data is updated
                Log.d(TAG,"onDataChange : Added information to database: \n" + dataSnapshot.getValue());
            }
            @Override
            public void onCancelled(DatabaseError databaseError){
                //Failed to read value
                Log.w(TAG, "Failed to read value", databaseError.toException());
            }
        });

        //Button Functions
        material_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminViewMaterialActivity.class));
                Toast.makeText(getApplicationContext(), "View Materials", Toast.LENGTH_LONG).show();

            }
        });

        material_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // firebase save object
                String materialID = materialid_input.getText().toString();
                String materialname = materialname_input.getText().toString();
                String description = description_input.getText().toString();
                String pointPerKg = pointPerKg_input.getText().toString();

                Log.d(TAG, "Attempting to submit credentials. \n " + "Material Name : " + materialname + "\n" +
                        "Description : " + description + "\n" + "Point Per Kg : " + pointPerKg);

                // checking input
                if (!materialid_input.equals(null) && (!materialname_input.equals(null) && (!description_input.equals(null) && (!pointPerKg_input.equals(null)))))
                {
                    String id = databaseReference.push().getKey();
                    Material material = new Material(materialID, materialname, description, pointPerKg);
                    databaseReference.child("material").child(materialID).setValue(material);
                    startActivity(new Intent(getApplicationContext(), AdminActivity.class));

                }
                else{

                    if (TextUtils.isEmpty(materialID)){
                        Toast.makeText(getApplicationContext(), "Please key in material ID...", Toast.LENGTH_LONG).show();
                    }
                    if (TextUtils.isEmpty(materialname)){
                        Toast.makeText(getApplicationContext(), "Please key in material name...", Toast.LENGTH_LONG).show();
                    }
                    if (TextUtils.isEmpty(description)){
                        Toast.makeText(getApplicationContext(), "Please key in description...", Toast.LENGTH_LONG).show();
                    }
                    if (TextUtils.isEmpty(pointPerKg)){
                        Toast.makeText(getApplicationContext(), "Please key in pointPerKg...", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }
}
