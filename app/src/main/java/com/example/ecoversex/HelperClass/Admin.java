package com.example.ecoversex.HelperClass;

public class Admin {

    String adminName, adminPhone;

    public Admin() {
    }

    public Admin(String adminName, String adminPhone) {
        this.adminName = adminName;
        this.adminPhone = adminPhone;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }


    public String toString(){
        return ("Admin name : " + getAdminName() +
                "\nAdmin phone : " + getAdminPhone());
    }
}
