package com.example.ecoversex.GeneralActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecoversex.GeneralActivity.UserActivity;
import com.example.ecoversex.HelperClass.User;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "Add to Database";

    private String userID;
    Button recycler_profile_edit_button, recycler_profile_save_button;
    EditText recycler_name_input,recycler_phone_input, recycler_point_input, recycler_address_input;
    TextView recycler_name_tv, recycler_phone_tv, recycler_point_tv, recycler_address_tv;


    //Firebase
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_profile);

        recycler_profile_edit_button = (Button) findViewById(R.id.recycler_profile_edit_button);
        recycler_profile_save_button = (Button) findViewById(R.id.recycler_profile_save_button);
        recycler_name_input = (EditText) findViewById(R.id.recycler_name_input);
        recycler_phone_input = (EditText) findViewById(R.id.recycler_phone_input);
        recycler_point_input = (EditText) findViewById(R.id.recycler_point_input);
        recycler_address_input = (EditText) findViewById(R.id.recycler_address_input);
        recycler_name_tv = (TextView) findViewById(R.id.recycler_name_tv);
        recycler_phone_tv = (TextView) findViewById(R.id.recycler_phone_tv);
        recycler_point_tv = (TextView) findViewById(R.id.recycler_point_tv);
        recycler_address_tv = (TextView) findViewById(R.id.recycler_address_tv);

        recycler_name_input.setVisibility(View.INVISIBLE);
        recycler_name_input.setEnabled(false);

        recycler_phone_input.setVisibility(View.INVISIBLE);
        recycler_phone_input.setEnabled(false);

        recycler_point_input.setVisibility(View.INVISIBLE);
        recycler_point_input.setEnabled(false);

        recycler_address_input.setVisibility(View.INVISIBLE);
        recycler_address_input.setEnabled(false);

        //Firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        userRef = firebaseDatabase.getReference().child("User").child(userID);

        /*userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.child("userName").getValue().toString();
                String phone = dataSnapshot.child("phone").getValue().toString();
                String point = dataSnapshot.child("ecoPoint").getValue().toString();
                String address = dataSnapshot.child("address").getValue().toString();
                recycler_name_tv.setText(userName);
                recycler_phone_tv.setText(phone);
                recycler_point_tv.setText(point);
                recycler_address_tv.setText(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        recycler_profile_save_button.setActivated(false);

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

        recycler_profile_edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycler_name_input.setVisibility(View.VISIBLE);
                recycler_name_input.setEnabled(true);

                recycler_phone_input.setVisibility(View.VISIBLE);
                recycler_phone_input.setEnabled(true);

                recycler_point_input.setVisibility(View.VISIBLE);
                recycler_point_input.setEnabled(true);

                recycler_address_input.setVisibility(View.VISIBLE);
                recycler_address_input.setEnabled(true);

                recycler_profile_save_button.setVisibility(View.VISIBLE);
                recycler_profile_save_button.setActivated(true);

                recycler_profile_edit_button.setVisibility(View.INVISIBLE);
                recycler_profile_edit_button.setActivated(false);

                recycler_name_tv.setVisibility(View.INVISIBLE);
                recycler_phone_tv.setVisibility(View.INVISIBLE);
                recycler_point_input.setVisibility(View.INVISIBLE);
                recycler_address_tv.setVisibility(View.INVISIBLE);

            }
        });

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

        recycler_profile_save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = recycler_name_input.getText().toString().trim();
                String phone = recycler_phone_input.getText().toString().trim();
                String address = recycler_address_input.getText().toString().trim();
                String point = "0";

                Log.d(TAG, "Attempting to submit credentials. \n " + "Name : " + userName + "\n" +
                        "Phone : " + phone + "\n" + "Address : " + address);

                // checking input
                if (!recycler_name_input.equals(null) && (!recycler_phone_input.equals(null)) && (!recycler_address_input.equals(null))){
                    User user = new User(userName, phone, address, point);
                    databaseReference.child("User").child(userID).setValue(user);

                    recycler_profile_save_button.setVisibility(View.INVISIBLE);
                    recycler_profile_save_button.setActivated(false);

                    recycler_profile_edit_button.setVisibility(View.VISIBLE);
                    recycler_profile_edit_button.setActivated(true);

                    recycler_name_input.setVisibility(View.INVISIBLE);
                    recycler_name_input.setEnabled(false);

                    recycler_phone_input.setVisibility(View.INVISIBLE);
                    recycler_phone_input.setEnabled(false);

                    recycler_point_input.setVisibility(View.INVISIBLE);
                    recycler_point_input.setEnabled(false);

                    recycler_address_input.setVisibility(View.INVISIBLE);
                    recycler_address_input.setEnabled(false);

                    recycler_name_tv.setVisibility(View.VISIBLE);
                    recycler_phone_tv.setVisibility(View.VISIBLE);
                    recycler_point_input.setVisibility(View.VISIBLE);
                    recycler_address_tv.setVisibility(View.VISIBLE);

                    startActivity(new Intent(getApplicationContext(), UserActivity.class));
                    Toast.makeText(getApplicationContext(), "User Main Page.", Toast.LENGTH_LONG).show();
                }

            }
        });




    }
}
