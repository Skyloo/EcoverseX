package com.example.ecoversex.DatabaseUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ecoversex.HelperClass.Material;
import com.example.ecoversex.R;

import java.util.ArrayList;
import java.util.List;

public class mDatabaseHandler extends SQLiteOpenHelper {
    public mDatabaseHandler(Context context) {
        super(context, mUtil.DATABASE_NAME, null, mUtil.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase materialDB) {
        String CREATE_MATERIAL_TABLE = "CREATE TABLE " + mUtil.TABLE_NAME + "(" + mUtil.KEY_ID + "INTEGER PRIMARY KEY, " + mUtil.KEY_NAME + "TEXT" +
                mUtil.KEY_DESCRIPTION + "TEXT" + mUtil.KEY_POINTPERKG + "TEXT" + ")";

        materialDB.execSQL(CREATE_MATERIAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase materialDB, int oldVersion, int newVersion) {
        String DROP_MATERIAL_TABLE = "DROP TABLE " + mUtil.TABLE_NAME;
        materialDB.execSQL(DROP_MATERIAL_TABLE, new String[]{mUtil.TABLE_NAME});

        onCreate(materialDB);
    }

    public void AddMaterial(Material material) {
        SQLiteDatabase materialDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(mUtil.KEY_ID, material.getMaterialID());
        contentValues.put(mUtil.KEY_NAME, material.getMaterialname());
        contentValues.put(mUtil.KEY_DESCRIPTION, material.getMaterialdescription());
        contentValues.put(mUtil.KEY_POINTPERKG, material.getPointPerKg());

        materialDB.insert(mUtil.TABLE_NAME, null, contentValues);
        materialDB.close();
    }

    public Material GetMaterial(String materialID) {
        SQLiteDatabase materialDB = this.getReadableDatabase();
        Cursor cursor = materialDB.query(mUtil.TABLE_NAME,
                new String[]{
                        mUtil.KEY_ID, mUtil.KEY_NAME,
                        mUtil.KEY_DESCRIPTION, mUtil.KEY_POINTPERKG
                }, mUtil.KEY_ID + "=?",
                new String[]{String.valueOf(materialID)}, null, null, null
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Material material = new Material(materialID, null,null,null);
        material.setMaterialID(cursor.getString(0));
        material.setMaterialname(cursor.getString(1));
        material.setMaterialdescription(cursor.getString(2));
        material.setPointPerKg(cursor.getString(3));
        materialDB.close();
        return material;
    }

    public List<Material> GetAllMaterial() {
        List<Material> materialList = new ArrayList<>();
        SQLiteDatabase materialDB = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + mUtil.TABLE_NAME;

        Cursor cursor = materialDB.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Material material = new Material(null,null,null,null);
                material.setMaterialID(cursor.getString(0));
                material.setMaterialname(cursor.getString(1));
                material.setMaterialdescription(cursor.getString(2));
                material.setPointPerKg(cursor.getString(3));

                materialList.add(material);
            } while (cursor.moveToNext());
        }
        materialDB.close();
        return materialList;
    }

    public void DeleteMaterial(Material material){
        SQLiteDatabase materialDB = this.getWritableDatabase();
        materialDB.delete(mUtil.TABLE_NAME,
                mUtil.KEY_ID + "=?",
                new String[]{String.valueOf(material.getMaterialID())});
        materialDB.close();
    }

    public int UpdateMaterial(Material material){

        SQLiteDatabase materialDB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(mUtil.KEY_NAME, material.getMaterialname());
        contentValues.put(mUtil.KEY_DESCRIPTION, material.getMaterialdescription());
        contentValues.put(mUtil.KEY_POINTPERKG, material.getPointPerKg());

        return materialDB.update(mUtil.TABLE_NAME, contentValues,
                mUtil.KEY_ID + "=?",
                new String[]{String.valueOf(material.getMaterialID())});
    }

}
