package com.example.ecoversex.RecyclerActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecyclerProfileActivity extends AppCompatActivity {

    private static final String TAG = "Add to Database";

    private String userID;
    Button recycler_profile_edit_button, recycler_profile_save_button;
    TextView recycler_name_input,recycler_phone_input, recycler_point_input;

    //Firebase
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_profile);

        recycler_profile_edit_button = (Button) findViewById(R.id.recycler_profile_edit_button);
        recycler_profile_save_button = (Button) findViewById(R.id.recycler_profile_save_button);
        recycler_name_input = (TextView) findViewById(R.id.recycler_name_input);
        recycler_phone_input = (TextView) findViewById(R.id.recycler_phone_input);
        recycler_point_input = (TextView) findViewById(R.id.recycler_point_input);

        //Firebase
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


    }
}
