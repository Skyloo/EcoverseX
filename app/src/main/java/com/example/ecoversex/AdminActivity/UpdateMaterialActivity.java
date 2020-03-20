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
import com.example.ecoversex.HelperClass.Material;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateMaterialActivity extends AppCompatActivity {

    private static final String TAG = "Update Database";

    //Get material ID and set new particulars
    private String materialID, userID;
    EditText update_materialname_input, update_description_input, update_pointPerKg_input;
    Material material;
    Button update_material_button, update_material_back_button;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    public void BackButton(View view){
        startActivity(new Intent(getApplicationContext(), AdminViewMaterialActivity.class));
        Toast.makeText(getApplicationContext(), "Return to View Material Page", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_material);

        update_description_input = (EditText)findViewById(R.id.update_description_input);
        update_materialname_input = (EditText)findViewById(R.id.update_materialname_input);
        update_pointPerKg_input = (EditText)findViewById(R.id.update_pointPerKg_input);
        update_material_button = (Button)findViewById(R.id.update_material_button);
        update_material_back_button = (Button)findViewById(R.id.update_material_back_button);

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

        update_material_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // firebase save object
                //get materialID based on cursor click

                DatabaseReference MID = FirebaseDatabase.getInstance().getReference("material");
                String materialID = MID.toString();
                String materialname = update_materialname_input.getText().toString();
                String description = update_description_input.getText().toString();
                String pointPerKg = update_pointPerKg_input.getText().toString();

                Log.d(TAG, "Attempting to submit credentials. \n " + "Material Name : " + materialname + "\n" +
                        "Description : " + description + "\n" + "Point Per Kg : " + pointPerKg);

                // checking input
                if (!materialname.equals(null) && (!description.equals(null) && (!pointPerKg.equals(null))))
                {
                    Material material = new Material(materialname, description, pointPerKg);
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
