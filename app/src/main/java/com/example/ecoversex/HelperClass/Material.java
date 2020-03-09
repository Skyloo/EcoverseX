package com.example.ecoversex.HelperClass;

public class Material {

    private int genericMatID = 1;
    private String materialID;
    private String materialname;
    private String materialdescription;
    private String pointPerKg;

    public Material(String materialID, String materialname, String materialdescription, String pointPerKg){
        this.materialID = materialID;
        this.materialname = materialname;
        this.materialdescription = materialdescription;
        this.pointPerKg = pointPerKg;
    }

    public int getGenericMatID() {
        return genericMatID;
    }
    public void setGenericMatID(int genericMatID) {
        this.genericMatID = genericMatID;
    }
    public String getMaterialID() {
        return materialID;
    }
    public void setMaterialID(String materialID) {
        this.materialID = String.format("SN%03d", genericMatID++);
    }
    public String getMaterialname() {
        return materialname;
    }
    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }
    public String getMaterialdescription() {
        return materialdescription;
    }
    public void setMaterialdescription(String materialdescription) {
        this.materialdescription = materialdescription;
    }
    public String getPointPerKg() {
        return pointPerKg;
    }
    public void setPointPerKg(String pointPerKg) {
        this.pointPerKg = pointPerKg;
    }

    public String toString(){
        return ("Material ID : " + getMaterialID() +
                "\nMaterial Name : " + getMaterialname() +
                "\nDescription : " + getMaterialdescription() +
                "\nPoint Per Kg : " + getPointPerKg());

    }

}
