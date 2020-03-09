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

import com.example.ecoversex.HelperClass.Admin;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminProfileActivity extends AppCompatActivity {

    private static final String TAG = "Add to Database";

    private Button admin_profile_back_button, admin_profile_save_button;
    EditText admin_name_input, admin_phone_input;
    private String userID;

    //Add Database Stuff
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseReference;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        admin_profile_back_button = (Button) findViewById(R.id.admin_profile_back_button);
        admin_profile_save_button = (Button) findViewById(R.id.admin_profile_save_button);
        admin_name_input = (EditText) findViewById(R.id.admin_name_input);
        admin_phone_input = (EditText) findViewById(R.id.admin_phone_input);

        firebaseAuth = FirebaseAuth.getInstance();
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

        admin_profile_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                Toast.makeText(AdminProfileActivity.this , "Home", Toast.LENGTH_LONG).show();
            }
        });

        admin_profile_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // firebase save object
                String adminName = admin_name_input.getText().toString();
                String adminPhone = admin_phone_input.getText().toString();

                Log.d(TAG, "Attempting to submit credentials. \n " + "Name : " + adminName + "\n" +
                        "Phone : " + adminPhone);

                // checking input
                if (!admin_name_input.equals(null) && (!admin_phone_input.equals(null))){
                    Admin admin = new Admin(adminName, adminPhone);
                    databaseReference.child("admin").child(userID).setValue(admin);

                }
                else {
                    if (TextUtils.isEmpty(adminName)){
                        Toast.makeText(AdminProfileActivity.this, "Please key in your Email...", Toast.LENGTH_LONG).show();
                    }
                    if (TextUtils.isEmpty(adminPhone)){
                        Toast.makeText(AdminProfileActivity.this, "Please key in your Phone Number...", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }
}
