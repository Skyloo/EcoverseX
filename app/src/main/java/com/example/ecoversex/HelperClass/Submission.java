package com.example.ecoversex.HelperClass;

public class Submission {

    private String submissionID;
    private String proposedDate;
    private String actualDate;
    private String weight;
    private String pointAwarded;
    private String status;
    private String materialName;
    private Material material;
    private User user;

    public Submission() {
    }

    public Submission(String submissionID, String proposedDate, String actualDate,String materialName, String weight, String pointAwarded, String status) {
        this.submissionID = submissionID;
        this.proposedDate = proposedDate;
        this.actualDate = actualDate;
        this.materialName = materialName;
        this.weight = weight;
        this.pointAwarded = pointAwarded;
        this.status = status;
    }

    public String getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(String submissionID) {
        this.submissionID = submissionID;
    }

    public String getProposedDate() {
        return proposedDate;
    }

    public void setProposedDate(String proposedDate) {
        this.proposedDate = proposedDate;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPointAwarded() {
        return pointAwarded;
    }

    public void setPointAwarded(String pointAwarded) {
        this.pointAwarded = pointAwarded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String toString(){
        return ("Submission ID : " + getSubmissionID() +
                "\nProposed Date : " + getProposedDate() +
                "\nActual Date : " + getActualDate() +
                "\nMaterial Name : " + getMaterialName() +
                "\nWeight : " + getWeight() +
                "\nPoints Awarded : " + getPointAwarded() +
                "\nStatus : " + getStatus());

    }
}
