package com.example.ecoversex.AdminActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

//import com.example.ecoversex.DatabaseUtil.mDatabaseHandler;
import com.example.ecoversex.HelperClass.Material;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AdminViewMaterialActivity extends AppCompatActivity {

    Button admin_add_material_button, admin_update_material_button, admin_delete_material_button;
    ListView admin_material_list;

    //Aggregation of Material
    Material material;

    //Firebase
    DatabaseReference materialRef;

    //Database
    ArrayList<String> materialList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_material);

        /*admin_add_material_button = (Button) findViewById(R.id.admin_add_material_button);
        admin_update_material_button = (Button) findViewById(R.id.admin_update_material_button);
        admin_delete_material_button = (Button) findViewById(R.id.admin_delete_material_button);*/
        admin_material_list = (ListView)findViewById(R.id.admin_material_list);

        materialRef = FirebaseDatabase.getInstance().getReference("material").getParent();

        final ArrayAdapter<String> materialAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,materialList);
        admin_material_list.setAdapter(materialAdapter);
        materialRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String mValue = dataSnapshot.getValue(String.class);
                materialList.add(mValue);
                materialAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                materialAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*admin_add_material_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddMaterialActivity.class);
                startActivity(intent);
            }
        });

        admin_update_material_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (material!= null){
                    startActivity(new Intent(getApplicationContext(),UpdateMaterialActivity.class));
                    Toast.makeText(getApplicationContext(), "Update Material Page", Toast.LENGTH_LONG).show();

                }
            }
        });*/
    }
}
