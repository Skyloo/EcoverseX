package com.example.ecoversex.RecyclerActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.ecoversex.HelperClass.Redemption;
import com.example.ecoversex.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecyclerRedemptionPage extends AppCompatActivity {

    ListView redemption_listview;

    String [] redemptionItem = {"Laptop","Iphone","Towel","Water Bottle","Floor Mat","Earphones","Mouse","Air-Conditioner"};
    String [] desc = {"A portable computer","An Iphone", "A bathing towel","A drinking water bottle","A floor mat", "A pair of earphones","Laptop/computer mouse","An air-cond"};
    Integer [] img = {R.drawable.laptop, R.drawable.iphone, R.drawable.towel, R.drawable.waterbottle, R.drawable.floormat, R.drawable.earphone, R.drawable.mouse, R.drawable.aircond};
    Integer [] redemptionPoint = {5000000, 100000000, 1000, 800, 1500, 200000, 800000, 3000000};

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_redemption_page);

        firebaseAuth = FirebaseAuth.getInstance();

        redemption_listview = (ListView) findViewById(R.id.redemption_listview);

        final List<HashMap<String, String>> redemptionList = new ArrayList<>();

        for (int i = 0; i < 8; i++){
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("image",Integer.toString(img[i]));
            hashMap.put("redemption item", redemptionItem[i]);
            hashMap.put("description", desc[i]);
            hashMap.put("redemption point", Integer.toString(redemptionPoint[i]));
            redemptionList.add(hashMap);
        }

        String [] from = {"image", "redemption item", "description", "redemption point"};
        int [] to = {R.id.redemption_imageview, R.id.redemption_name_textview,  R.id.redemption_description_textview, R.id.redemption_point_textview};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), redemptionList, R.layout.redemption_list_layout, from, to);
        redemption_listview.setAdapter(simpleAdapter);


    }
}
