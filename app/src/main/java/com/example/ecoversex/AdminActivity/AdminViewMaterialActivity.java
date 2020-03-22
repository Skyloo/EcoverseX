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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminViewMaterialActivity extends AppCompatActivity {

    Button admin_add_material_button, admin_update_material_button, admin_delete_material_button;
    ListView admin_material_list;

    //Aggregation of Material
    Material material;

    //Firebase
    DatabaseReference materialRef;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;

    //Database ArrayList
    ArrayList<Material> materialList;
    ArrayAdapter<Material> materialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_material);

        firebaseAuth = FirebaseAuth.getInstance();

        admin_add_material_button = (Button) findViewById(R.id.admin_add_material_button);
        admin_update_material_button = (Button) findViewById(R.id.admin_update_material_button);
        admin_delete_material_button = (Button) findViewById(R.id.admin_delete_material_button);
        admin_material_list = (ListView)findViewById(R.id.admin_material_list);

        firebaseDatabase = FirebaseDatabase.getInstance();
        materialRef = firebaseDatabase.getReference("material");

        material = new Material();
        materialList = new ArrayList<>();
        materialAdapter = new ArrayAdapter<Material>(this,android.R.layout.simple_list_item_1,materialList);

        materialRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {

                    material = ds.getValue(Material.class);
                    materialList.add(material);
                }
                admin_material_list.setAdapter(materialAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        admin_add_material_button.setOnClickListener(new View.OnClickListener() {
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
        });
    }
}
