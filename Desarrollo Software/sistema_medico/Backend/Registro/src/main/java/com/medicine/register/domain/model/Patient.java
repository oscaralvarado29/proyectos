package com.medicine.register.domain.model;

public class Patient {
    private String id;
    private String firstName;
    private String secondName;
    private String firstSurName;
    private String secondSurName;
    private String address;
    private String email;
    private String landline;
    private String cellPhone;
    private String residencesType;
    private String descriptionResidence;
    private String neighborhood;
    private String password;

    public Patient() {
        //El constructor es vacio porque se llena con el mapper
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setFirstSurName(String firstSurName) {
        this.firstSurName = firstSurName;
    }

    public void setSecondSurName(String secondSurName) {
        this.secondSurName = secondSurName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setResidencesType(String residencesType) {
        this.residencesType = residencesType;
    }

    public void setDescriptionResidence(String descriptionResidence) {
        this.descriptionResidence = descriptionResidence;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
