package com.example.ecoversex.AdminActivity;

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

import java.util.List;

public class AdminViewMaterialActivity extends AppCompatActivity {

    Button admin_add_material_button, admin_update_material_button,admin_delete_material_button;
    ListView admin_material_list;

    Material material;
    //mDatabaseHandler mdatabaseHandler;
    private FirebaseAuth firebaseAuth;

    /*public void GetAllMaterial() {
        material = null;
        List<Material> materialList = mdatabaseHandler.GetAllMaterial();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, materialList);
        admin_material_list.setAdapter(arrayAdapter);
    }

    public void DeleteButton(){
        if (material!=null){
            mdatabaseHandler.DeleteMaterial(material);
            GetAllMaterial();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        GetAllMaterial();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_material);

        admin_add_material_button = (Button) findViewById(R.id.admin_add_material_button);
        admin_update_material_button = (Button) findViewById(R.id.admin_update_material_button);
        admin_delete_material_button = (Button) findViewById(R.id.admin_delete_material_button);
        admin_material_list = (ListView)findViewById(R.id.admin_material_list);

        firebaseAuth = FirebaseAuth.getInstance();

        /*admin_material_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                material = (Material) admin_material_list.getItemAtPosition(position);
                for (int i = 0; i < admin_material_list.getChildCount(); i++) {
                    if (position == 1) {
                        admin_material_list.getChildAt(i).setBackgroundColor(Color.GRAY);
                    } else {
                        admin_material_list.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });
        GetAllMaterial();*/

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
