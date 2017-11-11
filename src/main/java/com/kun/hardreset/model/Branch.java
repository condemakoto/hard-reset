package com.kun.hardreset.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Branch {
    @javax.persistence.Id
    private String Id;
    private String Name;
    private String Category;
    private String End_Attention;
    private String start_Attention;
    private String Phone_number;
    private String Street_number;
    private String Street_name;
    private String Notes;
    private String City;
    private String State;
    private String Zip;
    private double Lat;
    private double Lng;

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getCategory() {
        return Category;
    }

    public String getEnd_Attention() {
        return End_Attention;
    }

    public String getStart_Attention() {
        return start_Attention;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public String getStreet_number() {
        return Street_number;
    }

    public String getStreet_name() {
        return Street_name;
    }

    public String getNotes() {
        return Notes;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getZip() {
        return Zip;
    }

    public double getLat() {
        return Lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setStreet_name(String street_name) {
        Street_name = street_name;
    }
}
