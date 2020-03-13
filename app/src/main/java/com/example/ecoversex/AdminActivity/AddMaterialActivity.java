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

    /*private static final String TAG = "Add to Database";*/

    private Button material_back_button, material_save_button;
    EditText name_input, description_input, pointPerKg_input;
    private String userID, materialID;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    //mDatabaseHandler mdatabaseHandler;

    /*public void AddMaterial(View view) {
        if (!name_input.equals(null) && (!description_input.equals(null) && (!pointPerKg_input.equals(null)))) {
            String materialname = name_input.toString().trim();
            String description = description_input.toString().trim();
            String pointPerKg = pointPerKg_input.toString().trim();

            Material material = new Material(materialname, description, pointPerKg);
            material.setMaterialname(materialname);
            material.setDescription(description);
            material.setPointPerKg(pointPerKg);
            mdatabaseHandler.AddMaterial(material);
            mdatabaseHandler.close();
            finish();
        } else {
            if (TextUtils.isEmpty(name_input.toString())) {
                Toast.makeText(AddMaterialActivity.this, "Please key in your material name...", Toast.LENGTH_LONG).show();
            }
            if (TextUtils.isEmpty(description_input.toString())) {
                Toast.makeText(AddMaterialActivity.this, "Please key in your material description...", Toast.LENGTH_LONG).show();
            }
            if (TextUtils.isEmpty(pointPerKg_input.toString())) {
                Toast.makeText(AddMaterialActivity.this, "Please key in your material points per kg...", Toast.LENGTH_LONG).show();
            }
        }
    }*/

    public void BackButton(View view){
        startActivity(new Intent(getApplicationContext(), AdminViewMaterialActivity.class));
        Toast.makeText(getApplicationContext(), "View Materials", Toast.LENGTH_LONG).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_material);

        material_back_button = (Button) findViewById(R.id.material_back_button);
        material_save_button = (Button) findViewById(R.id.material_save_button);
        name_input = (EditText) findViewById(R.id.materialname_input);
        description_input = (EditText) findViewById(R.id.description_input);
        pointPerKg_input = (EditText) findViewById(R.id.pointPerKg_input);

        firebaseAuth = FirebaseAuth.getInstance();
        //Materials using firebase
        /*firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();*/

        /*databaseReference.addValueEventListener(new ValueEventListener() {
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
        });*/

        /*material_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // firebase save object
                String materialID = material_id_input.getText().toString();
                String materialname = materialname_input.getText().toString();
                String description = description_input.getText().toString();
                String pointPerKg = pointPerKg_input.getText().toString();

                Log.d(TAG, "Attempting to submit credentials. \n " + "Material Name : " + materialname + "\n" +
                        "Description : " + description + "\n" + "Point Per Kg : " + pointPerKg);

                // checking input
                if (!material_id_input.equals(null) && (!materialname_input.equals(null) && (!description_input.equals(null) && (!pointPerKg_input.equals(null))){
                    Material material = new Material(materialname, description, pointPerKg);
                    databaseReference.child("material").child(materialID).setValue(material);
                    startActivity(new Intent(getApplicationContext(), AdminActivity.class));

                }
                else {
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
        });*/




    }
}
