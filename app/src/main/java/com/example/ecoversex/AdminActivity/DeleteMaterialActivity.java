package com.example.ecoversex.AdminActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ecoversex.HelperClass.Material;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteMaterialActivity extends AppCompatActivity {

    EditText delete_material_edittext;
    Button delete_material_confirm_button;
    ListView short_material_list;

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
        setContentView(R.layout.activity_delete_material);

        firebaseAuth = FirebaseAuth.getInstance();

        delete_material_edittext = (EditText)findViewById(R.id.delete_material_edittext);
        delete_material_confirm_button = (Button)findViewById(R.id.delete_material_confirm_button);
        short_material_list = (ListView)findViewById(R.id.short_material_list);

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
                short_material_list.setAdapter(materialAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        delete_material_confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String materialID = delete_material_edittext.getText().toString().trim();

                materialRef.child(materialID).removeValue();
                Toast.makeText(getApplicationContext(), "Material" + Material.class.toString() + " is Deleted.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
            }
        });

    }
}
