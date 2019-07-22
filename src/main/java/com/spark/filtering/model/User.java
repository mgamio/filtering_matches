package com.spark.filtering.model;

public class User {
    private String display_name;
    private int age;
    private String job_title;
    private int height_in_cm;
    private City city;
    private String main_photo;
    private double compatibility_score;
    private int contacts_exchanged;
    private Boolean favourite;
    private String religion;

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public int getHeight_in_cm() {
        return height_in_cm;
    }

    public void setHeight_in_cm(int height_in_cm) {
        this.height_in_cm = height_in_cm;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getMain_photo() {
        return main_photo;
    }

    public void setMain_photo(String main_photo) {
        this.main_photo = main_photo;
    }

    public double getCompatibility_score() {
        return compatibility_score;
    }

    public void setCompatibility_score(double compatibility_score) {
        this.compatibility_score = compatibility_score;
    }

    public int getContacts_exchanged() {
        return contacts_exchanged;
    }

    public void setContacts_exchanged(int contacts_exchanged) {
        this.contacts_exchanged = contacts_exchanged;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }
}
