package com.example.ecoversex.HelperClass;

import android.widget.ArrayAdapter;


public class Redemption{

    private String redemptionItem;
    private String desc;
    private int img;
    private int redemptionPoint;

    public Redemption() {
    }

    public Redemption(String redemptionItem, String desc, int img, int redemptionPoint) {
        this.redemptionItem = redemptionItem;
        this.desc = desc;
        this.img = img;
        this.redemptionPoint = redemptionPoint;
    }

    public String getRedemptionItem() {
        return redemptionItem;
    }

    public void setRedemptionItem(String redemptionItem) {
        this.redemptionItem = redemptionItem;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getRedemptionPoint() {
        return redemptionPoint;
    }

    public void setRedemptionPoint(int redemptionPoint) {
        this.redemptionPoint = redemptionPoint;
    }

    public String toString(){
        return "";
    }
}
