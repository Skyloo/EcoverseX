package com.example.ecoversex.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecoversex.DatabaseUtil.mDatabaseHandler;
import com.example.ecoversex.HelperClass.Material;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateMaterialActivity extends AppCompatActivity {

    //Get material ID and set new particulars
    private String materialID, userID;
    EditText update_materialname_input, update_description_input, update_pointPerKg_input;
    Material material;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;

    mDatabaseHandler mdatabaseHandler;

    public void UpdateMaterial(View view) {
        String materialname = update_materialname_input.toString().trim();
        String description = update_description_input.toString().trim();
        String pointPerKg = update_pointPerKg_input.toString().trim();
        if (!update_materialname_input.equals(null) && (!update_description_input.equals(null) && (!update_pointPerKg_input.equals(null)))) {
            Material material = new Material(materialID,materialname,description,pointPerKg);
            material.setMaterialname(materialname);
            material.setMaterialdescription(description);
            material.setPointPerKg(pointPerKg);
            mdatabaseHandler.AddMaterial(material);
            mdatabaseHandler.close();
            finish();
        }
        else {
            if (TextUtils.isEmpty(update_materialname_input.toString())) {
                Toast.makeText(UpdateMaterialActivity.this, "Please key in your material name...", Toast.LENGTH_LONG).show();
            }
            if (TextUtils.isEmpty(update_description_input.toString())) {
                Toast.makeText(UpdateMaterialActivity.this, "Please key in your material description...", Toast.LENGTH_LONG).show();
            }
            if (TextUtils.isEmpty(update_pointPerKg_input.toString())) {
                Toast.makeText(UpdateMaterialActivity.this, "Please key in your material points per kg...", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_material);

        firebaseAuth = FirebaseAuth.getInstance();

        update_description_input = (EditText)findViewById(R.id.update_description_input);
        update_materialname_input = (EditText)findViewById(R.id.update_materialname_input);
        update_pointPerKg_input = (EditText)findViewById(R.id.update_pointPerKg_input);


    }
}
