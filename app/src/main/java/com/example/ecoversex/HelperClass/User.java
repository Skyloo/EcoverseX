package com.example.ecoversex.HelperClass;

public abstract class User {

    private String userName;
    private String phoneNo;
    private String address;
    private String ecoPoints;
    private Material material;

    public User() {
    }

    public User(String userName, String phoneNo, String address, String ecoPoints, Material material) {
        this.userName = userName;
        this.phoneNo = phoneNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }


    public String toString(){
        return ("Name : " + getUserName() +
                "\nPhone No : " + getPhoneNo());

    }
}
