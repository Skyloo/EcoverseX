package com.example.ecoversex.RecyclerActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ecoversex.HelperClass.Material;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecyclerViewMaterialActivity extends AppCompatActivity {

    public static final String MATERIAL_ID = "materialID";
    public static final String MATERIAL_NAME = "materialname";
    public static final String POINT_PER_KG = "pointPerKg";

    ListView material_list;

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
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_material);

        firebaseAuth = FirebaseAuth.getInstance();

        material_list = (ListView)findViewById(R.id.material_list);

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
                material_list.setAdapter(materialAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        material_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Material material = materialList.get(position);
                Intent intent = new Intent(getApplicationContext(), ProposeDialogActivity.class);

                intent.putExtra(MATERIAL_NAME, material.getMaterialname());
                intent.putExtra(MATERIAL_ID, material.getMaterialID());
                intent.putExtra(POINT_PER_KG, material.getPointPerKg());

                startActivity(intent);

            }
        });


    }
}
