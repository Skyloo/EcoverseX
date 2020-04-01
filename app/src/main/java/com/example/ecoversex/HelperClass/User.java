package com.example.ecoversex.HelperClass;

public class User {

    private String userName;
    private String phoneNo;
    private String address;
    private String ecoPoints;

    public User() {
    }

    public User(String userName, String phoneNo, String address, String ecoPoints) {
        this.userName = userName;
        this.phoneNo = phoneNo;
        this.address = address;
        this.ecoPoints = ecoPoints;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEcoPoints() {
        return ecoPoints;
    }

    public void setEcoPoints(String ecoPoints) {
        this.ecoPoints = ecoPoints;
    }


    public String toString(){
        return ("Name : " + getUserName() +
                "\nPhone No : " + getPhoneNo() +
                "\nAddress : " + getAddress() +
                "\nEco Points : " +getEcoPoints());

    }
}
