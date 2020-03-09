package com.example.ecoversex.AdminActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecoversex.DatabaseUtil.mDatabaseHandler;
import com.example.ecoversex.HelperClass.Material;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMaterialActivity extends AppCompatActivity {

    private Button material_back_button, material_save_button;
    EditText name_input, description_input, pointPerKg_input;
    private String userID, materialID;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;
    mDatabaseHandler mdatabaseHandler;

    public void AddMaterial(View view) {
        if (!name_input.equals(null) && (!description_input.equals(null) && (!pointPerKg_input.equals(null)))) {
            String materialname = name_input.toString().trim();
            String materialDescription = description_input.toString().trim();
            String pointPerKg = pointPerKg_input.toString().trim();

            Material material = new Material(materialID, materialname, materialDescription, pointPerKg);
            material.setMaterialname(materialname);
            material.setMaterialdescription(materialDescription);
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
    }
    public void backButton(){
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

    }
}
