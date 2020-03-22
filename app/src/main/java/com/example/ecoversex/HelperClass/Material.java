package com.example.ecoversex.HelperClass;

public class Material {

    private String materialID;
    private String materialname;
    private String description;
    private String pointPerKg;

    public Material(){}

    public Material(String materialID, String materialname, String description, String pointPerKg){
        this.materialID = materialID;
        this.materialname = materialname;
        this.description = description;
        this.pointPerKg = pointPerKg;
    }

    public String getMaterialID() {
        return materialID;
    }
    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }
    public String getMaterialname() {
        return materialname;
    }
    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPointPerKg() {
        return pointPerKg;
    }
    public void setPointPerKg(String pointPerKg) {
        this.pointPerKg = pointPerKg;
    }

    public String toString(){
        return ("Material ID : " +getMaterialID() +
                "\nMaterial Name : " + getMaterialname() +
                "\nDescription : " + getDescription() +
                "\nPoint Per Kg : " + getPointPerKg());

    }

}
